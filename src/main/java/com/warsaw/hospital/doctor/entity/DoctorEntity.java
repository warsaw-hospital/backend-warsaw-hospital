package com.warsaw.hospital.doctor.entity;

import com.warsaw.hospital.user.entity.UserEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "doctor")
public class DoctorEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String lastname;
  private String email;
  private String password;
  private String personalCode;
  private String specialization;
  private String phoneNumber;
  private String description;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserEntity user;

  public Long getId() {
    return id;
  }

  public DoctorEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public DoctorEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getLastname() {
    return lastname;
  }

  public DoctorEntity setLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public DoctorEntity setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public DoctorEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getPersonalCode() {
    return personalCode;
  }

  public DoctorEntity setPersonalCode(String personalCode) {
    this.personalCode = personalCode;
    return this;
  }

  public String getSpecialization() {
    return specialization;
  }

  public DoctorEntity setSpecialization(String specialization) {
    this.specialization = specialization;
    return this;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public DoctorEntity setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public DoctorEntity setDescription(String address) {
    this.description = address;
    return this;
  }

  public UserEntity getUser() {
    return user;
  }

  public DoctorEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DoctorEntity that = (DoctorEntity) o;
    return getId().equals(that.getId())
        && getName().equals(that.getName())
        && getLastname().equals(that.getLastname())
        && getEmail().equals(that.getEmail())
        && getPassword().equals(that.getPassword())
        && getSpecialization().equals(that.getSpecialization())
        && getPersonalCode().equals(that.getPersonalCode())
        && getPhoneNumber().equals(that.getPhoneNumber())
        && getDescription().equals(that.getDescription());
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
        getSpecialization(),
        getPhoneNumber(),
        getDescription());
  }
}
