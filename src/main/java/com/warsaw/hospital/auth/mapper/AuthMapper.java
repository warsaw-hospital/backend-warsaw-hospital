package com.warsaw.hospital.auth.mapper;

import com.warsaw.hospital.auth.web.response.UserInfoResponse;
import com.warsaw.hospital.user.entity.UserEntity;
import com.warsaw.hospital.user.entity.UserToUserRoleEntity;
import com.warsaw.hospital.userrole.entity.UserRoleEntity;
import com.warsaw.hospital.userrole.mapper.UserRoleMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AuthMapper {
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
        .setLastname(user.getSurname())
        .setRoles(roles.stream().map(UserRoleMapper::toResponse).collect(Collectors.toList()))
        .setPersonalCode(user.getPersonalCode())
        .setEmail(user.getEmail())
        .setFullyCreated(user.getFullyCreated())
        .setPhoneNumber(user.getPhoneNumber())
        .setAddress(user.getAddress())
        .setType(user.getUserType());
  }
}
