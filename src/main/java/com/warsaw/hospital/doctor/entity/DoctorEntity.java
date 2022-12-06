package com.warsaw.hospital.doctor.entity;

import com.warsaw.hospital.user.entity.UserToDoctorEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
  private String phoneNumber;
  private String description;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor", orphanRemoval = true)
  private List<DoctorToSpecializationEntity> specializations = new ArrayList<>();

  @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
  private UserToDoctorEntity user;

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

  public List<DoctorToSpecializationEntity> getSpecializations() {
    return specializations;
  }

  public DoctorEntity setSpecializations(List<DoctorToSpecializationEntity> specializations) {
    this.specializations = specializations;
    return this;
  }

  public DoctorEntity addSpecialization(DoctorSpecializationEntity specialization) {
    DoctorToSpecializationEntity doctorToSpecializationEntity = new DoctorToSpecializationEntity();
    doctorToSpecializationEntity.setDoctor(this);
    doctorToSpecializationEntity.setSpecialization(specialization);
    return this;
  }

  public DoctorEntity removeSpecialization(DoctorSpecializationEntity specialization) {
    specializations.removeIf(
        doctorToSpecializationEntity ->
            doctorToSpecializationEntity.getSpecialization().equals(specialization));
    specialization
        .getDoctors()
        .removeIf(
            doctorToSpecializationEntity ->
                doctorToSpecializationEntity.getSpecialization().equals(specialization));
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

  public UserToDoctorEntity getUser() {
    return user;
  }

  public DoctorEntity setUser(UserToDoctorEntity user) {
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
        && getSpecializations().equals(that.getSpecializations())
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
        getSpecializations(),
        getPhoneNumber(),
        getDescription());
  }
}
