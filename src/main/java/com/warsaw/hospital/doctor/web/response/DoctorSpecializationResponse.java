package com.warsaw.hospital.doctor.web.response;

import com.warsaw.hospital.doctor.enums.DoctorSpecializationEnum;

import java.util.Objects;

public class DoctorSpecializationResponse {
  private Long id;

  private DoctorSpecializationEnum name;

  private String description;

  public Long getId() {
    return id;
  }

  public DoctorSpecializationResponse setId(Long id) {
    this.id = id;
    return this;
  }

  public DoctorSpecializationEnum getName() {
    return name;
  }

  public DoctorSpecializationResponse setName(DoctorSpecializationEnum name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public DoctorSpecializationResponse setDescription(String description) {
    this.description = description;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DoctorSpecializationResponse that = (DoctorSpecializationResponse) o;
    return getId().equals(that.getId())
        && getName() == that.getName()
        && Objects.equals(getDescription(), that.getDescription());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getDescription());
  }
}
