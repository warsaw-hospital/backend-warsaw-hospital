package com.warsaw.hospital.auth.web.request;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class RegisterRequest {
  @NotEmpty private String name;

  @NotEmpty private String lastname;

  @NotEmpty private String email;

  @NotEmpty private String password;

  @NotEmpty private String personalCode;

  @NotEmpty private String phoneNumber;

  @NotEmpty private String address;

  public String getName() {
    return name;
  }

  public RegisterRequest setName(String name) {
    this.name = name;
    return this;
  }

  public String getLastname() {
    return lastname;
  }

  public RegisterRequest setLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public RegisterRequest setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public RegisterRequest setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getPersonalCode() {
    return personalCode;
  }

  public RegisterRequest setPersonalCode(String personalCode) {
    this.personalCode = personalCode;
    return this;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public RegisterRequest setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public RegisterRequest setAddress(String address) {
    this.address = address;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RegisterRequest that = (RegisterRequest) o;
    return getName().equals(that.getName())
        && getLastname().equals(that.getLastname())
        && getEmail().equals(that.getEmail())
        && getPassword().equals(that.getPassword())
        && getPersonalCode().equals(that.getPersonalCode())
        && getPhoneNumber().equals(that.getPhoneNumber())
        && getAddress().equals(that.getAddress());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getName(),
        getLastname(),
        getEmail(),
        getPassword(),
        getPersonalCode(),
        getPhoneNumber(),
        getAddress());
  }
}
