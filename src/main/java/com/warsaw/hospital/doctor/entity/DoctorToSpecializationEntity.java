package com.warsaw.hospital.doctor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warsaw.hospital.doctor.enums.DoctorSpecializationEnum;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "doctor_to_specialization")
public class DoctorToSpecializationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id", referencedColumnName = "id")
  @JsonIgnore()
  private DoctorEntity doctor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "specialization_id", referencedColumnName = "id")
  @JsonIgnore()
  private DoctorSpecializationEntity specialization;

  public Long getId() {
    return id;
  }

  public DoctorToSpecializationEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public DoctorEntity getDoctor() {
    return doctor;
  }

  public DoctorToSpecializationEntity setDoctor(DoctorEntity doctor) {
    this.doctor = doctor;
    return this;
  }

  public DoctorSpecializationEntity getSpecialization() {
    return specialization;
  }

  public DoctorToSpecializationEntity setSpecialization(DoctorSpecializationEntity specialization) {
    this.specialization = specialization;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DoctorToSpecializationEntity that = (DoctorToSpecializationEntity) o;
    return getId().equals(that.getId())
        && getDoctor().equals(that.getDoctor())
        && getSpecialization().equals(that.getSpecialization());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getDoctor(), getSpecialization());
  }
}
