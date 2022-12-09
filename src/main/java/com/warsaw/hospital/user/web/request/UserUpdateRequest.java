package com.warsaw.hospital.user.web.request;

import javax.validation.constraints.NotNull;
import java.util.Objects;

//TODO: update UserUpdateRequest
public class UserUpdateRequest {
  @NotNull private String phoneNumber;
  @NotNull private String address;

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public UserUpdateRequest setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public UserUpdateRequest setAddress(String address) {
    this.address = address;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserUpdateRequest)) return false;
    UserUpdateRequest that = (UserUpdateRequest) o;
    return getPhoneNumber().equals(that.getPhoneNumber()) && getAddress().equals(that.getAddress());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPhoneNumber(), getAddress());
  }
}
