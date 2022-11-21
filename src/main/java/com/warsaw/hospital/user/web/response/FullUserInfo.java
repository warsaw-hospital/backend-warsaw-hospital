package com.warsaw.hospital.user.web.response;

import com.warsaw.hospital.auth.web.response.UserInfoResponse;

import java.util.Objects;

public class FullUserInfo {
  private Long id;
  private UserInfoResponse userInfo;

  public Long getId() {
    return id;
  }

  public FullUserInfo setId(Long id) {
    this.id = id;
    return this;
  }

  public UserInfoResponse getUserInfo() {
    return userInfo;
  }

  public FullUserInfo setUserInfo(UserInfoResponse userInfo) {
    this.userInfo = userInfo;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof FullUserInfo)) return false;
    FullUserInfo that = (FullUserInfo) o;
    return getId().equals(that.getId()) && getUserInfo().equals(that.getUserInfo());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUserInfo());
  }
}
