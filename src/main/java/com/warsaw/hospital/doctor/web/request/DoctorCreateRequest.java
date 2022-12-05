package com.warsaw.hospital.doctor.web.request;

import com.warsaw.hospital.doctor.enums.DoctorSpecializationEnum;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class DoctorCreateRequest {
  @NotEmpty private String name;
  @NotEmpty private String lastname;
  @NotEmpty @Email private String email;
  @NotEmpty private String password;
  @NotEmpty private String personalCode;
  @NotEmpty private DoctorSpecializationEnum specialization;
  @NotEmpty private String phoneNumber;
  @NotEmpty private String description;
  @NotEmpty private String address;

  public String getName() {
    return name;
  }

  public DoctorCreateRequest setName(String name) {
    this.name = name;
    return this;
  }

  public String getLastname() {
    return lastname;
  }

  public DoctorCreateRequest setLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public DoctorCreateRequest setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public DoctorCreateRequest setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getPersonalCode() {
    return personalCode;
  }

  public DoctorCreateRequest setPersonalCode(String personalCode) {
    this.personalCode = personalCode;
    return this;
  }

  public DoctorSpecializationEnum getSpecialization() {
    return specialization;
  }

  public DoctorCreateRequest setSpecialization(DoctorSpecializationEnum specialization) {
    this.specialization = specialization;
    return this;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public DoctorCreateRequest setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public DoctorCreateRequest setDescription(String description) {
    this.description = description;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public DoctorCreateRequest setAddress(String address) {
    this.address = address;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DoctorCreateRequest that = (DoctorCreateRequest) o;
    return getName().equals(that.getName())
        && getLastname().equals(that.getLastname())
        && getEmail().equals(that.getEmail())
        && getPassword().equals(that.getPassword())
        && getPersonalCode().equals(that.getPersonalCode())
        && getSpecialization() == that.getSpecialization()
        && getPhoneNumber().equals(that.getPhoneNumber())
        && getDescription().equals(that.getDescription())
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
        getSpecialization(),
        getPhoneNumber(),
        getDescription(),
        getAddress());
  }
}
