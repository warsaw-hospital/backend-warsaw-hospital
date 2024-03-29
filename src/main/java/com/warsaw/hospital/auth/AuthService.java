package com.warsaw.hospital.auth;

import com.warsaw.hospital.auth.config.AuthenticatedProfile;
import com.warsaw.hospital.auth.utils.CookieUtil;
import com.warsaw.hospital.auth.utils.JwtUtil;
import com.warsaw.hospital.auth.web.request.LoginRequest;
import com.warsaw.hospital.auth.web.response.StatusResponse;
import com.warsaw.hospital.doctor.DoctorService;
import com.warsaw.hospital.user.UserService;
import com.warsaw.hospital.user.entity.UserEntity;
import com.warsaw.hospital.user.entity.UserToDoctorEntity;
import com.warsaw.hospital.user.entity.UserToUserRoleEntity;
import com.warsaw.hospital.userrole.UserRoleService;
import com.warsaw.hospital.userrole.entity.UserRoleEntity;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

  private final UserService userService;
  private final DoctorService doctorService;
  private final PasswordEncoder encoder;
  private final JwtUtil jwtUtil;
  private final CookieUtil cookieUtil;
  private final UserRoleService userRoleService;
  // Public for testing
  @Value("${app.generated-password-length}")
  public Integer PASSWORD_LENGTH;

  @Value("${app.BASE_URL}")
  private String BASE_URL;

  public AuthService(
      UserService userService,
      DoctorService doctorService,
      PasswordEncoder encoder,
      JwtUtil jwtUtil,
      CookieUtil cookieUtil,
      UserRoleService userRoleService) {
    this.userService = userService;
    this.encoder = encoder;
    this.doctorService = doctorService;
    this.jwtUtil = jwtUtil;
    this.cookieUtil = cookieUtil;
    this.userRoleService = userRoleService;
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

  public Boolean register(UserEntity user, HttpServletResponse response) {
    user.setLastLogin(LocalDateTime.now())
        .setPassword(encoder.encode(user.getPassword()))
        .setCreatedAt(LocalDateTime.now());
    UserToUserRoleEntity toUserRole =
        new UserToUserRoleEntity().setUserRole(userRoleService.findById(1L));
    user.addToUserRole(toUserRole);
     addAuthorityCookie(user, response);
    userService.create(user);

    return true;
  }

  public Boolean doctorLogin(LoginRequest request, HttpServletResponse response) {
    String email = request.getEmail();
    String rawPassword = request.getPassword();

    Optional<UserEntity> maybeUser = userService.maybeFindByEmail(email);

    if (maybeUser.isEmpty() || !encoder.matches(rawPassword, maybeUser.get().getPassword())) {
      return false;
    }

    //    Optional<DoctorEntity> maybeDoctor =
    // doctorService.maybeFindByUserId(maybeUser.get().getId());
    //    if (maybeDoctor.isEmpty()) {
    //      return false;
    //    }

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
