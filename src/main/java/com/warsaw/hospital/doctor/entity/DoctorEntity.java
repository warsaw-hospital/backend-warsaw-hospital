package com.warsaw.hospital.doctor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  private String specialization;
  private String phone_number;
  private String address;

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

  public String getSpecialization() {
    return specialization;
  }

  public DoctorEntity setSpecialization(String specialization) {
    this.specialization = specialization;
    return this;
  }

  public String getPhone_number() {
    return phone_number;
  }

  public DoctorEntity setPhone_number(String phone_number) {
    this.phone_number = phone_number;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public DoctorEntity setAddress(String address) {
    this.address = address;
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
        && getPhone_number().equals(that.getPhone_number())
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
        getSpecialization(),
        getPhone_number(),
        getAddress());
  }
}
