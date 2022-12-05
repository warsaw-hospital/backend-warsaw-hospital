package com.warsaw.hospital.doctor.web.response;

import java.util.Objects;

public class DoctorResponse {
  private Long id;
  private String name;
  private String lastname;
  private String specialization;
  private String email;
  private String phoneNumber;
  private String description;

  public Long getId() {
    return id;
  }

  public DoctorResponse setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public DoctorResponse setName(String name) {
    this.name = name;
    return this;
  }

  public String getLastname() {
    return lastname;
  }

  public DoctorResponse setLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public String getSpecialization() {
    return specialization;
  }

  public DoctorResponse setSpecialization(String specialization) {
    this.specialization = specialization;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public DoctorResponse setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public DoctorResponse setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public DoctorResponse setDescription(String description) {
    this.description = description;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DoctorResponse that = (DoctorResponse) o;
    return getId().equals(that.getId())
        && getName().equals(that.getName())
        && getLastname().equals(that.getLastname())
        && getSpecialization().equals(that.getSpecialization())
        && getEmail().equals(that.getEmail())
        && getPhoneNumber().equals(that.getPhoneNumber())
        && getDescription().equals(that.getDescription());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(),
        getName(),
        getLastname(),
        getSpecialization(),
        getEmail(),
        getPhoneNumber(),
        getDescription());
  }
}
