package com.warsaw.hospital.doctor.entity;

import com.warsaw.hospital.doctor.enums.DoctorSpecializationEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "specialization")
public class DoctorSpecializationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  @Enumerated(EnumType.STRING)
  private DoctorSpecializationEnum name;

  private String description;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "specialization", orphanRemoval = true)
  private List<DoctorToSpecializationEntity> doctors = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public DoctorSpecializationEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public DoctorSpecializationEnum getName() {
    return name;
  }

  public DoctorSpecializationEntity setName(DoctorSpecializationEnum name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public DoctorSpecializationEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public List<DoctorToSpecializationEntity> getDoctors() {
    return doctors;
  }

  public DoctorSpecializationEntity setDoctors(List<DoctorToSpecializationEntity> doctors) {
    this.doctors = doctors;
    return this;
  }

  public DoctorSpecializationEntity addDoctor(DoctorEntity doctor) {
    DoctorToSpecializationEntity doctorToSpecializationEntity = new DoctorToSpecializationEntity();
    doctorToSpecializationEntity.setDoctor(doctor);
    doctorToSpecializationEntity.setSpecialization(this);
    doctors.add(doctorToSpecializationEntity);
    doctor.getSpecializations().add(doctorToSpecializationEntity);
    return this;
  }

  public DoctorSpecializationEntity removeDoctor(DoctorEntity doctor) {
    doctors.removeIf(
        doctorToSpecializationEntity -> doctorToSpecializationEntity.getDoctor().equals(doctor));
    doctor
        .getSpecializations()
        .removeIf(
            doctorToSpecializationEntity ->
                doctorToSpecializationEntity.getSpecialization().equals(this));
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DoctorSpecializationEntity that = (DoctorSpecializationEntity) o;
    return getId().equals(that.getId())
        && getName() == that.getName()
        && Objects.equals(getDescription(), that.getDescription());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getDescription());
  }
}
