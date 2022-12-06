package com.warsaw.hospital.doctor.web.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class DoctorUpdateRequest {

  @NotNull(message = "id is ${validatedValue}, but it must not be null")
  private Long id;

  @NotEmpty(message = "name is ${validatedValue}, but it must not be null")
  private String name;

  @NotEmpty(message = "lastname is ${validatedValue}, but it must not be null")
  private String lastname;

  @NotEmpty(message = "email is ${validatedValue}, but it must not be null")
  @Email
  private String email;

  @NotEmpty(message = "password is ${validatedValue}, but it must not be null")
  private String password;

  @NotEmpty(message = " personalCode is ${validatedValue}, but it must not be null")
  private String personalCode;

  @NotEmpty(message = "phoneNumber is ${validatedValue}, but it must not be null")
  private String phoneNumber;

  @NotEmpty(message = "description is ${validatedValue}, but it must not be null")
  private String description;

  @NotEmpty(message = "address is ${validatedValue}, but it must not be null")
  private String address;

  @NotEmpty(message = "specializationIds is ${validatedValue}, but it must not be null")
  private List<Long> specializationIds;

  public Long getId() {
    return id;
  }

  public DoctorUpdateRequest setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public DoctorUpdateRequest setName(String name) {
    this.name = name;
    return this;
  }

  public String getLastname() {
    return lastname;
  }

  public DoctorUpdateRequest setLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public DoctorUpdateRequest setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public DoctorUpdateRequest setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getPersonalCode() {
    return personalCode;
  }

  public DoctorUpdateRequest setPersonalCode(String personalCode) {
    this.personalCode = personalCode;
    return this;
  }

  public List<Long> getSpecializationIds() {
    return specializationIds;
  }

  public DoctorUpdateRequest setSpecializationIds(List<Long> specializationIds) {
    this.specializationIds = specializationIds;
    return this;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public DoctorUpdateRequest setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public DoctorUpdateRequest setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public DoctorUpdateRequest setAddress(String address) {
    this.address = address;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DoctorUpdateRequest that = (DoctorUpdateRequest) o;
    return getId().equals(that.getId())
        && getName().equals(that.getName())
        && getLastname().equals(that.getLastname())
        && getEmail().equals(that.getEmail())
        && getPassword().equals(that.getPassword())
        && getPersonalCode().equals(that.getPersonalCode())
        && getSpecializationIds() == that.getSpecializationIds()
        && getPhoneNumber().equals(that.getPhoneNumber())
        && getDescription().equals(that.getDescription())
        && getAddress().equals(that.getAddress());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(),
        getName(),
        getLastname(),
        getEmail(),
        getPassword(),
        getPersonalCode(),
        getSpecializationIds(),
        getPhoneNumber(),
        getDescription(),
        getAddress());
  }
}
