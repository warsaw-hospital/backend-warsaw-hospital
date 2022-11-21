package com.warsaw.hospital.user.controller;

import com.warsaw.hospital.user.UserService;
import com.warsaw.hospital.user.entity.UserEntity;
import com.warsaw.hospital.user.mapper.FullUserInfoMapper;
import com.warsaw.hospital.user.web.response.FullUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/v1/user")
public class UserAdminController {

  private final UserService service;

  public UserAdminController(UserService service) {
    this.service = service;
  }

  @Operation(summary = "Get all user accounts.")
  @GetMapping
  public List<FullUserInfo> findAll() {
    return FullUserInfoMapper.toInfoResponses(service.findAll());
  }

  @Operation(summary = "Get all user accounts.")
  @GetMapping("/filter")
  public List<FullUserInfo> findAllFilteredBy(@Parameter String search) {
    List<UserEntity> users = service.findAll(search);
    return FullUserInfoMapper.toInfoResponses(users);
  }

  @Operation(summary = "Get user account by id.")
  @GetMapping("/{id}")
  public FullUserInfo findById(@PathVariable Long id) {
    return FullUserInfoMapper.toInfoResponse(service.findById(id));
  }

  //  @Operation(summary = "Update user account.")
  //  @PostMapping("/{id}")
  //  public FullUserInfo update(
  //      @PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
  //
  //  }
}
