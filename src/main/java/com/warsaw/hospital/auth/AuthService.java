package com.warsaw.hospital.auth;

import com.warsaw.hospital.auth.config.AuthenticatedProfile;
import com.warsaw.hospital.auth.utils.CookieUtil;
import com.warsaw.hospital.auth.utils.JwtUtil;
import com.warsaw.hospital.auth.utils.PasswordUtil;
import com.warsaw.hospital.auth.web.request.LoginRequest;
import com.warsaw.hospital.auth.web.request.RegisterRequest;
import com.warsaw.hospital.auth.web.response.StatusResponse;
import com.warsaw.hospital.user.UserService;
import com.warsaw.hospital.user.entity.UserEntity;
import com.warsaw.hospital.user.entity.UserToDoctorEntity;
import com.warsaw.hospital.user.entity.UserToUserRoleEntity;
import com.warsaw.hospital.userrole.UserRoleService;
import com.warsaw.hospital.userrole.entity.UserRoleEntity;
import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService {
  private final UserService userService;
  private final PasswordEncoder encoder;
  private final JwtUtil jwtUtil;
  private final CookieUtil cookieUtil;
  private final UserRoleService userRoleService;
  private final PasswordUtil passwordUtil;
  // Public for testing
  @Value("${app.generated-password-length}")
  public Integer PASSWORD_LENGTH;

  public AuthService(
      UserService userService,
      PasswordEncoder encoder,
      JwtUtil jwtUtil,
      CookieUtil cookieUtil,
      UserRoleService userRoleService,
      PasswordUtil passwordUtil) {
    this.userService = userService;
    this.encoder = encoder;
    this.jwtUtil = jwtUtil;
    this.cookieUtil = cookieUtil;
    this.userRoleService = userRoleService;
    this.passwordUtil = passwordUtil;
  }

  /**
   * This method controls user password and username login. It does by: 1) validating the user; 2)
   * creating a JWT token and adding the token to authorization cookie.
   *
   * @param request username and password login request.
   * @param response information about the request.
   */
  public Boolean login(LoginRequest request, HttpServletResponse response) {
    String email = request.getEmail();
    String rawPassword = request.getPassword();

    Optional<UserEntity> maybeUser = userService.maybeFindByEmail(email);
    if (maybeUser.isEmpty() || !encoder.matches(rawPassword, maybeUser.get().getPassword())) {
      return false;
    }

    UserEntity user = maybeUser.get();
    userService.update(user.setLastLogin(LocalDateTime.now()));
    addAuthorityCookie(maybeUser.get(), response);
    return true;
  }

  public Boolean register(RegisterRequest request, HttpServletResponse response) {
    UserEntity user = createUser(request);
    user.setPassword(encoder.encode(user.getPassword())).setCreatedAt(LocalDateTime.now());
    addAuthorityCookie(user, response);
    userService.create(user);

    return true;
  }

  public UserEntity createUser(RegisterRequest request) {
    LocalDateTime now = LocalDateTime.now();
    String name = request.getName();
    String surname = request.getLastname();
    String email = request.getEmail();
    String phone = request.getPhoneNumber();

    String address = request.getAddress();
    String personalCode = request.getPersonalCode();
    String password = passwordUtil.generate(PASSWORD_LENGTH);

    UserEntity userEntity =
        new UserEntity()
            .setName(name)
            .setLastname(surname)
            .setEmail(email)
            .setPhoneNumber(phone)
            .setPersonalCode(personalCode)
            .setAddress(address)
            .setPassword(password)
            .setLastLogin(now);
    UserToUserRoleEntity toUserRole =
        new UserToUserRoleEntity().setUserRole(userRoleService.findById(1L));
    userEntity.addToUserRole(toUserRole);

    return userEntity;
  }

  public Boolean doctorLogin(LoginRequest request, HttpServletResponse response) {
    String email = request.getEmail();
    String rawPassword = request.getPassword();

    Optional<UserEntity> maybeUser = userService.maybeFindByEmail(email);

    if (maybeUser.isEmpty() || !encoder.matches(rawPassword, maybeUser.get().getPassword())) {
      return false;
    }

    UserToDoctorEntity userToDoctor = maybeUser.get().getDoctor();
    if (userToDoctor == null) {
      return false;
    }

    UserEntity user = maybeUser.get();
    userService.update(user.setLastLogin(LocalDateTime.now()));
    addAuthorityCookie(maybeUser.get(), response);
    return true;
  }

  /**
   * When a user logs off, we update the user's last logoff time and delete the authorization
   * cookie.
   *
   * @param profile The profile object that was created when the user logged in.
   * @param response The HttpServletResponse object.
   */
  public void logoff(AuthenticatedProfile profile, HttpServletResponse response) {
    UserEntity user = userService.findById(profile.getUserId());
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
    System.out.println("token" + token);
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

  public StatusResponse getStatus(@Nullable AuthenticatedProfile profile) {
    StatusResponse result = new StatusResponse();
    if (profile == null) {
      return result.setIsLoggedIn(false);
    }
    UserEntity user = userService.findById(profile.getUserId());
    boolean isAdmin =
        user.getRoles().stream().anyMatch(role -> role.getUserRole().getName().equals("ADMIN"));
    return result.setIsLoggedIn(true).setIsDoctor(isAdmin);
  }
}
