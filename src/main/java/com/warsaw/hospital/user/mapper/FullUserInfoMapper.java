package com.warsaw.hospital.user.mapper;

import com.warsaw.hospital.auth.mapper.AuthMapper;
import com.warsaw.hospital.user.entity.UserEntity;
import com.warsaw.hospital.user.web.response.FullUserInfo;

import java.util.List;
import java.util.stream.Collectors;

public class FullUserInfoMapper {
  public static FullUserInfo toInfoResponse(UserEntity entity) {
    return new FullUserInfo().setId(entity.getId()).setUserInfo(AuthMapper.toResponse(entity));
  }

  public static List<FullUserInfo> toInfoResponses(List<UserEntity> users) {
    return users.stream().map(FullUserInfoMapper::toInfoResponse).collect(Collectors.toList());
  }
}
