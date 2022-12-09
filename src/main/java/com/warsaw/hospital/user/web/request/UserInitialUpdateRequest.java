package com.warsaw.hospital.user.web.request;

import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.Objects;

public class UserInitialUpdateRequest {
  @NotNull private String email;
  @NotNull private String phoneNumber;
  @NotNull private String address;
  @NotNull private String password;

  public String getEmail() {
    return email;
  }

  public UserInitialUpdateRequest setEmail(String email) {
    this.email = email.toLowerCase(Locale.ROOT);
    return this;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public UserInitialUpdateRequest setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public UserInitialUpdateRequest setAddress(String address) {
    this.address = address;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserInitialUpdateRequest setPassword(String password) {
    this.password = password;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserInitialUpdateRequest)) return false;
    UserInitialUpdateRequest that = (UserInitialUpdateRequest) o;
    return getEmail().equals(that.getEmail())
        && getPhoneNumber().equals(that.getPhoneNumber())
        && getAddress().equals(that.getAddress())
        && getPassword().equals(that.getPassword());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getEmail(), getPhoneNumber(), getAddress(), getPassword());
  }
}
