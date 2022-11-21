package com.warsaw.hospital.auth;

import com.warsaw.hospital.auth.config.AuthenticatedProfile;
import com.warsaw.hospital.auth.web.request.EmailPasswordLoginRequest;
import com.warsaw.hospital.auth.web.response.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  @Value("${app.BASE_URL}")
  private String BASE_URL;

  private final AuthService service;

  public AuthController(AuthService service) {
    this.service = service;
  }

  @Operation(summary = "Check if current user is logged in")
  @GetMapping("/status")
  public StatusResponse isLoggedIn(AuthenticatedProfile profile) {
    return service.getStatus(profile);
  }

  @Operation(summary = "Checks and returns if the token is valid")
  @GetMapping("/password/token/valid/{passChangeToken}")
  public Boolean isPassChangeTokenValid(@PathVariable String passChangeToken) {
    Boolean valid = service.isValid(passChangeToken);
    System.out.println(valid);
    return valid;
  }

  @Operation(summary = "Login by passing username and password request")
  @PostMapping("/login")
  public Boolean login(
      @RequestBody EmailPasswordLoginRequest request, HttpServletResponse httpResponse) {
    return service.login(request, httpResponse);
  }

  @Operation(summary = "Log off from account")
  @PostMapping("/logoff")
  public void logoff(AuthenticatedProfile profile, HttpServletResponse httpResponse) {
    service.logoff(profile, httpResponse);
  }

  @Operation(summary = "Remind username and password by sending a mail to given email")
  @PostMapping("/password/remind")
  public void passwordReminder(@Parameter @Email String email) {
    service.remindPassword(email.toLowerCase(Locale.ROOT));
  }

  @Operation(summary = "Change password by using password change token")
  @PostMapping("/password/token/change")
  public void changePassword(@Parameter String newPassword, @Parameter String passChangeToken) {
    service.changePassword(newPassword, passChangeToken);
  }
}
