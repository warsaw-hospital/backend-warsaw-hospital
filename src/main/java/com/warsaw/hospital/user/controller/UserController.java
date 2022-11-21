package com.warsaw.hospital.user.controller;

import com.warsaw.hospital.auth.config.AuthenticatedProfile;
import com.warsaw.hospital.auth.mapper.AuthMapper;
import com.warsaw.hospital.auth.web.response.UserInfoResponse;
import com.warsaw.hospital.user.UserService;
import com.warsaw.hospital.user.entity.UserEntity;
import com.warsaw.hospital.user.web.request.UserInitialUpdateRequest;
import com.warsaw.hospital.user.web.request.UserUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @Operation(summary = "Get user info using authenticated profile")
  @GetMapping("/me")
  public UserInfoResponse getUserInfo(AuthenticatedProfile profile) {
    UserEntity user = service.getUser(profile);
    return AuthMapper.toResponse(user);
  }

  @Operation(summary = "Change password by using authenticated profile")
  @PostMapping("/change/password")
  public Boolean changePassword(
      @Parameter(required = true) String newPassword,
      @Parameter(required = true) String oldPassword,
      AuthenticatedProfile profile) {
    return service.changePassword(newPassword, oldPassword, profile);
  }

  @Operation(summary = "Change password by using authenticated profile")
  @PostMapping("/change/info")
  public UserInfoResponse changeUserInfo(
      @RequestBody @Valid UserUpdateRequest request, AuthenticatedProfile profile) {
    UserEntity user = service.updateBy(request, profile);
    return AuthMapper.toResponse(user);
  }

  @Operation(summary = "Initial user info update")
  @PostMapping("/update/initial")
  public UserInfoResponse initialUpdate(
      @RequestBody @Valid UserInitialUpdateRequest request, AuthenticatedProfile profile) {
    UserEntity user = service.updateBy(request, profile);
    return AuthMapper.toResponse(user);
  }
}
