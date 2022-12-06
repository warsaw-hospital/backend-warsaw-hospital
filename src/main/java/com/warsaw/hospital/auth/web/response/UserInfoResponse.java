package com.warsaw.hospital.auth.web.response;

import com.warsaw.hospital.userrole.web.response.UserRoleResponse;

import java.util.List;
import java.util.Objects;

public class UserInfoResponse {
  private Boolean loggedIn;
  private String name;
  private String lastname;
  private String email;
  private String phoneNumber;
  private String address;
  private List<UserRoleResponse> roles;

  public Boolean getLoggedIn() {
    return loggedIn;
  }

  public UserInfoResponse setLoggedIn(Boolean loggedIn) {
    this.loggedIn = loggedIn;
    return this;
  }

  public String getName() {
    return name;
  }

  public UserInfoResponse setName(String name) {
    this.name = name;
    return this;
  }

  public String getLastname() {
    return lastname;
  }

  public UserInfoResponse setLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public List<UserRoleResponse> getRoles() {
    return roles;
  }

  public UserInfoResponse setRoles(List<UserRoleResponse> roles) {
    this.roles = roles;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserInfoResponse setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public UserInfoResponse setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public UserInfoResponse setAddress(String address) {
    this.address = address;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserInfoResponse)) return false;
    UserInfoResponse that = (UserInfoResponse) o;
    return getLoggedIn().equals(that.getLoggedIn())
        && Objects.equals(getName(), that.getName())
        && Objects.equals(getLastname(), that.getLastname())
        && Objects.equals(getEmail(), that.getEmail())
        && Objects.equals(getPhoneNumber(), that.getPhoneNumber())
        && Objects.equals(getAddress(), that.getAddress())
        && Objects.equals(getRoles(), that.getRoles());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getLoggedIn(),
        getName(),
        getLastname(),
        getEmail(),
        getPhoneNumber(),
        getAddress(),
        getRoles());
  }
}
