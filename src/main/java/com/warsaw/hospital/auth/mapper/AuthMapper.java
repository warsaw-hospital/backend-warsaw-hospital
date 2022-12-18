package com.warsaw.hospital.auth.mapper;

import com.warsaw.hospital.auth.web.request.RegisterRequest;
import com.warsaw.hospital.auth.web.response.UserInfoResponse;
import com.warsaw.hospital.user.entity.UserEntity;
import com.warsaw.hospital.user.entity.UserToUserRoleEntity;
import com.warsaw.hospital.userrole.entity.UserRoleEntity;
import com.warsaw.hospital.userrole.mapper.UserRoleMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AuthMapper {

  public static UserEntity toEntity(RegisterRequest request) {
    return new UserEntity()
        .setName(request.getName())
        .setLastname(request.getLastname())
        .setEmail(request.getEmail())
        .setPersonalCode(request.getPersonalCode())
        .setPassword(request.getPassword())
        .setPhoneNumber(request.getPhoneNumber())
        .setAddress(request.getAddress());
  }

  public static UserInfoResponse toResponse(UserEntity user) {
    UserInfoResponse response = new UserInfoResponse();
    if (user == null) {
      return response.setLoggedIn(false);
    }

    List<UserRoleEntity> roles =
        user.getRoles().stream()
            .map(UserToUserRoleEntity::getUserRole)
            .collect(Collectors.toList());
    return response
        .setLoggedIn(true)
        .setName(user.getName())
        .setLastname(user.getLastname())
        .setRoles(roles.stream().map(UserRoleMapper::toResponse).collect(Collectors.toList()))
        .setEmail(user.getEmail())
        .setPhoneNumber(user.getPhoneNumber())
        .setAddress(user.getAddress());
  }
}
