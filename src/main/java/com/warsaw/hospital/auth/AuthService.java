package com.warsaw.hospital.auth;

import com.warsaw.hospital.auth.config.AuthenticatedProfile;
import com.warsaw.hospital.auth.utils.CookieUtil;
import com.warsaw.hospital.auth.utils.JwtUtil;
import com.warsaw.hospital.auth.utils.PasswordUtil;
import com.warsaw.hospital.auth.web.request.EmailPasswordLoginRequest;
import com.warsaw.hospital.auth.web.response.StatusResponse;
import com.warsaw.hospital.email.EmailService;
import com.warsaw.hospital.emailtemplate.EmailTemplateService;
import com.warsaw.hospital.emailtemplate.entity.EmailTemplateEntity;
import com.warsaw.hospital.exception.ApiException;
import com.warsaw.hospital.user.UserService;
import com.warsaw.hospital.user.entity.UserEntity;
import com.warsaw.hospital.user.entity.UserToUserRoleEntity;
import com.warsaw.hospital.userrole.UserRoleService;
import com.warsaw.hospital.userrole.entity.UserRoleEntity;
import com.warsaw.hospital.utils.LocalDateTimeUtil;
import io.micrometer.core.lang.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

  private static final String EMAIL_TEMPLATE_NAME = "PASSWORD_REMINDER";
  private static final String PASS_CHANGE_COMPLETE_EMAIL_TEMPLATE_NAME =
      "PASSWORD_REMINDER_COMPLETE";
  private static final String PASS_CHANGE_TOKEN_KEY = "PASS_CHANGE_TOKEN";
  private static final String EMAIL_USER_EMAIL_KEY = "EMAIL";

  // Public for testing
  @Value("${app.generated-password-length}")
  public Integer PASSWORD_LENGTH;

  @Value("${app.BASE_URL}")
  private String BASE_URL;

  private final UserService userService;
  private final EmailTemplateService emailTemplateService;
  private final PasswordEncoder encoder;
  private final PasswordUtil passwordUtil;
  private final JwtUtil jwtUtil;
  private final CookieUtil cookieUtil;
  private final EmailService emailService;
  private final UserRoleService userRoleService;

  public AuthService(
      UserService userService,
      EmailTemplateService emailTemplateService,
      PasswordEncoder encoder,
      PasswordUtil passwordUtil,
      JwtUtil jwtUtil,
      CookieUtil cookieUtil,
      EmailService emailService,
      UserRoleService userRoleService) {
    this.userService = userService;
    this.emailTemplateService = emailTemplateService;
    this.encoder = encoder;
    this.passwordUtil = passwordUtil;
    this.jwtUtil = jwtUtil;
    this.cookieUtil = cookieUtil;
    this.emailService = emailService;
    this.userRoleService = userRoleService;
  }

  /**
   * This method controls user password and username login. It does by: 1) validating the user; 2)
   * creating a JWT token and adding the token to authorization cookie.
   *
   * @param request username and password login request.
   * @param response information about the request.
   */
  public Boolean login(EmailPasswordLoginRequest request, HttpServletResponse response) {
    String email = request.getEmail();
    String rawPassword = request.getPassword();

    // Get a user with correct username if he exists, else throw exception
    Optional<UserEntity> maybeUser = userService.maybeFindByEmail(email);
    if (maybeUser.isEmpty()
        || !encoder.matches(rawPassword, maybeUser.get().getPassword())
        || !maybeUser.get().getVerified()) {
      return false;
    }

    UserEntity user = maybeUser.get();
    userService.update(user.setLastLogin(LocalDateTime.now()));
    addAuthorityCookie(maybeUser.get(), response);
    return true;
  }

  public void logoff(AuthenticatedProfile profile, HttpServletResponse response) {
    // Cleanup
    UserEntity user = userService.findById(profile.getUserId());
    userService.update(user);

    cookieUtil.deleteAuthorizationCookie(response);
  }

  /**
   * This method creates a JWT token and adds the token to authorization cookie.
   *
   * @param user an authorized user.
   * @param response response, to which the cookie will be added.
   */
  private void addAuthorityCookie(UserEntity user, HttpServletResponse response) {
    String authorityString = getUserAuthorityString(user);
    List<GrantedAuthority> authorities =
        AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString);
    String token = jwtUtil.generateJwtToken(user.getId(), null, null, authorities);
    cookieUtil.addAuthorizationCookie(token, response);
  }

  /**
   * This method maps user roles to granted authorities.
   *
   * @param user authenticated user.
   * @return a list of granted authorities.
   */
  public String getUserAuthorityString(UserEntity user) {
    return user.getRoles().stream()
        .map(UserToUserRoleEntity::getUserRole)
        .map(UserRoleEntity::getName)
        .collect(Collectors.joining(","));
  }

  /**
   * This method send a password reminder letter to user. Also, it is implied that user email is
   * unique. An exception is thrown, when user does not exist.
   *
   * @param email email, which might have a user.
   */
  public void remindPassword(String email) {
    Optional<UserEntity> maybeUser = userService.maybeFindByEmail(email);
    if (maybeUser.isPresent()) {
      UserEntity user = maybeUser.get();
      EmailTemplateEntity emailTemplate = emailTemplateService.findByName(EMAIL_TEMPLATE_NAME);
      Map<String, String> data = getDataMapForPasswordReminding(user);

      String passChangeToken = data.get(PASS_CHANGE_TOKEN_KEY);
      userService.update(
          user.setPassChangeToken(passChangeToken).setTokenCreationDate(LocalDateTime.now()));
      emailService.send(emailTemplate, email, data);
    } else {
      LOGGER.warn("remind password: email[{}] do not exist in database", email);
    }
  }

  /**
   * This method creates a map with values to put into user password reminder email.
   *
   * @param user user whose password is forgotten.
   * @return map of values for user password reminding email.
   */
  public Map<String, String> getDataMapForPasswordReminding(UserEntity user) {
    Map<String, String> result = new HashMap<>();

    UUID uuid = UUID.randomUUID();
    result.put(EMAIL_USER_EMAIL_KEY, user.getEmail());
    result.put(PASS_CHANGE_TOKEN_KEY, uuid.toString());
    result.put("BASE_URL", BASE_URL);
    return result;
  }

  /**
   * This method changes user password by using password change token.
   *
   * @param newPassword the new password.
   * @param passChangeToken a one-time use token for changing user password.
   */
  public void changePassword(String newPassword, String passChangeToken) {
    UserEntity user = userService.findByPassChangeToken(passChangeToken);
    var tokenCreation = user.getTokenCreationDate();

    if (LocalDateTimeUtil.getHourCountFromDateToNow(tokenCreation) > 24) {
      // If pass change token is expired, then do not change password
      throw ApiException.bad("Nepavyko pakeisti slaptažodžio");
    }

    String encryptedPassword = encoder.encode(newPassword);
    user.setPassword(encryptedPassword).setPassChangeToken(null);
    userService.update(user);

    EmailTemplateEntity emailTemplate =
        emailTemplateService.findByName(PASS_CHANGE_COMPLETE_EMAIL_TEMPLATE_NAME);
    Map<String, String> data = new HashMap<>();
    data.put(EMAIL_USER_EMAIL_KEY, user.getEmail());
    emailService.send(emailTemplate, user.getEmail(), data);
  }

  /**
   * This method checks if the user exists by password change token.
   *
   * @param passChangeToken one-time use password change token.
   * @return true or false.
   */
  public Boolean isValid(String passChangeToken) {
    if (!userService.existsByPasswordChangeToken(passChangeToken)) {
      return false;
    }

    UserEntity user = userService.findByPassChangeToken(passChangeToken);
    var tokenCreation = user.getTokenCreationDate();
    return LocalDateTimeUtil.getHourCountFromDateToNow(tokenCreation) <= 24;
  }

  public StatusResponse getStatus(@Nullable AuthenticatedProfile profile) {
    StatusResponse result = new StatusResponse();
    if (profile == null) {
      return result.setLoggedIn(false);
    }
    UserEntity user = userService.findById(profile.getUserId());
    boolean isAdmin =
        user.getRoles().stream().anyMatch(role -> role.getUserRole().getName().equals("ADMIN"));
    return result.setLoggedIn(true).setAdmin(isAdmin);
  }

  public void sendWelcomeEmail(UserEntity user) {
    String templateName = AuthEmailUtil.getEmailTemplateName();
    EmailTemplateEntity template = emailTemplateService.findByName(templateName);
    Map<String, String> data = AuthEmailUtil.getDataForEmail(user);
    emailService.send(template, user.getEmail(), data);
  }
}
