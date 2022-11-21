package com.warsaw.hospital.userrole.mapper;

import com.warsaw.hospital.userrole.entity.UserRoleEntity;
import com.warsaw.hospital.userrole.web.request.UserRoleCreateRequest;
import com.warsaw.hospital.userrole.web.request.UserRoleUpdateRequest;
import com.warsaw.hospital.userrole.web.response.UserRoleResponse;

public class UserRoleMapper {
  public static UserRoleEntity toEntity(Long id, String name) {
    return new UserRoleEntity().setId(id).setName(name);
  }

  public static UserRoleEntity toEntity(UserRoleUpdateRequest request) {
    return UserRoleMapper.toEntity(request.getId(), request.getName());
  }

  public static UserRoleEntity toEntity(UserRoleCreateRequest request) {
    return UserRoleMapper.toEntity(null, request.getName());
  }

  public static UserRoleResponse toResponse(UserRoleEntity request) {
    return new UserRoleResponse().setId(request.getId()).setName(request.getName());
  }
}
