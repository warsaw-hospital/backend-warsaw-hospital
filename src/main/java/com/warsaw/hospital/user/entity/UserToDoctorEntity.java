package com.warsaw.hospital.user.entity;

import com.warsaw.hospital.doctor.entity.DoctorEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "user_to_doctor")
public class UserToDoctorEntity {
  @Id @GeneratedValue private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserEntity user;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id", referencedColumnName = "id")
  private DoctorEntity doctor;

  public UserToDoctorEntity(UserEntity user, DoctorEntity doctor) {
    this.user = user;
    this.doctor = doctor;
  }

  public UserToDoctorEntity() {}

  public Long getId() {
    return id;
  }

  public UserToDoctorEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public UserEntity getUser() {
    return user;
  }

  public UserToDoctorEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }

  public DoctorEntity getDoctor() {
    return doctor;
  }

  public UserToDoctorEntity setDoctor(DoctorEntity doctor) {
    this.doctor = doctor;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserToDoctorEntity that = (UserToDoctorEntity) o;
    return getId().equals(that.getId())
        && getUser().equals(that.getUser())
        && getDoctor().equals(that.getDoctor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUser(), getDoctor());
  }
}
