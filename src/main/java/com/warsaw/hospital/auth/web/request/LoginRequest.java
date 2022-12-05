package com.warsaw.hospital.auth.web.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Locale;
import java.util.Objects;

public class LoginRequest {
  @Email @NotEmpty private String email;
  @NotEmpty private String password;
  private String ipAddress;

  public String getEmail() {
    return email;
  }

  public LoginRequest setEmail(String email) {
    this.email = email.toLowerCase(Locale.ROOT);
    return this;
  }

  public String getPassword() {
    return password;
  }

  public LoginRequest setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public LoginRequest setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof LoginRequest)) return false;
    LoginRequest that = (LoginRequest) o;
    return getEmail().equals(that.getEmail())
        && getPassword().equals(that.getPassword())
        && getIpAddress().equals(that.getIpAddress());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getEmail(), getPassword(), getIpAddress());
  }
}
