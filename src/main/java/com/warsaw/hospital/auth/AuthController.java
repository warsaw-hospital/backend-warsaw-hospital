package com.warsaw.hospital.auth;

import com.warsaw.hospital.auth.config.AuthenticatedProfile;
import com.warsaw.hospital.auth.mapper.AuthMapper;
import com.warsaw.hospital.auth.web.request.LoginRequest;
import com.warsaw.hospital.auth.web.request.RegisterRequest;
import com.warsaw.hospital.auth.web.response.StatusResponse;
import com.warsaw.hospital.user.entity.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService service;

  public AuthController(AuthService service) {
    this.service = service;
  }

  @Operation(summary = "Check if current user is logged in")
  @GetMapping("/status")
  public StatusResponse isLoggedIn(AuthenticatedProfile profile) {
    return service.getStatus(profile);
  }

  @PostMapping("/register")
  public Boolean register(@RequestBody RegisterRequest request, HttpServletResponse httpResponse) {
    UserEntity user = AuthMapper.toEntity(request);
    return service.register(user, httpResponse);
  }

  @Operation(summary = "Login by passing username and password request")
  @PostMapping("/login")
  public Boolean login(@RequestBody LoginRequest request, HttpServletResponse httpResponse) {
    return service.login(request, httpResponse);
  }

  @Operation(summary = "Login to doctor by passing username and password request")
  @PostMapping("/doctor-login")
  public Boolean doctorLogin(@RequestBody LoginRequest request, HttpServletResponse httpResponse) {
    return service.doctorLogin(request, httpResponse);
  }

  @Operation(summary = "Log off from account")
  @PostMapping("/logout")
  public void logout(AuthenticatedProfile profile, HttpServletResponse httpResponse) {
    service.logoff(profile, httpResponse);
  }
}
