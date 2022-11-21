package com.warsaw.hospital.auth.web.response;

import com.warsaw.hospital.user.entity.enums.UserType;
import com.warsaw.hospital.userrole.web.response.UserRoleResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserInfoResponse {
  private Boolean loggedIn;
  private String name;
  private String lastname;
  private String personalCode;
  private String email;
  private String phoneNumber;
  private Boolean isTlkEnabled;
  private String evrkCode;
  private String address;
  private Boolean fullyCreated;
  private UserType type;
  private List<UserRoleResponse> roles;
  private String representedPersonCode;
  private Boolean mustCreateAccommodations;

  public String getRepresentedPersonCode() {
    return representedPersonCode;
  }

  public UserInfoResponse setRepresentedPersonCode(String representedPersonCode) {
    this.representedPersonCode = representedPersonCode;
    return this;
  }

  public Boolean getLoggedIn() {
    return loggedIn;
  }

  public UserInfoResponse setLoggedIn(Boolean loggedIn) {
    this.loggedIn = loggedIn;
    return this;
  }

  public Boolean getFullyCreated() {
    return fullyCreated;
  }

  public UserInfoResponse setFullyCreated(Boolean fullyCreated) {
    this.fullyCreated = fullyCreated;
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

  public String getPersonalCode() {
    return personalCode;
  }

  public UserInfoResponse setPersonalCode(String personalCode) {
    this.personalCode = personalCode;
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

  public Boolean getTlkEnabled() {
    return isTlkEnabled;
  }

  public UserInfoResponse setTlkEnabled(Boolean tlkEnabled) {
    isTlkEnabled = tlkEnabled;
    return this;
  }

  public String getEvrkCode() {
    return evrkCode;
  }

  public UserInfoResponse setEvrkCode(String evrkCode) {
    this.evrkCode = evrkCode;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public UserInfoResponse setAddress(String address) {
    this.address = address;
    return this;
  }

  public UserType getType() {
    return type;
  }

  public UserInfoResponse setType(UserType type) {
    this.type = type;
    return this;
  }

  public Boolean getMustCreateAccommodations() {
    return mustCreateAccommodations;
  }

  public UserInfoResponse setMustCreateAccommodations(Boolean mustCreateAccommodations) {
    this.mustCreateAccommodations = mustCreateAccommodations;
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
        && Objects.equals(getPersonalCode(), that.getPersonalCode())
        && Objects.equals(getEmail(), that.getEmail())
        && Objects.equals(getPhoneNumber(), that.getPhoneNumber())
        && Objects.equals(isTlkEnabled, that.isTlkEnabled)
        && getFullyCreated() == that.getFullyCreated()
        && getMustCreateAccommodations() == that.getMustCreateAccommodations()
        && Objects.equals(getEvrkCode(), that.getEvrkCode())
        && Objects.equals(getAddress(), that.getAddress())
        && getType() == that.getType()
        && Objects.equals(getRoles(), that.getRoles());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getLoggedIn(),
        getName(),
        getLastname(),
        getPersonalCode(),
        getEmail(),
        getFullyCreated(),
        getPhoneNumber(),
        isTlkEnabled,
        getMustCreateAccommodations(),
        getEvrkCode(),
        getAddress(),
        getType(),
        getRoles());
  }
}
