package com.warsaw.hospital.appointment.entity;

import com.warsaw.hospital.appointment.entity.enums.AppointmentStatusEnum;
import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.user.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity(name = "appointment")
public class AppointmentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;
  private LocalDate appointmentDate;
  private LocalTime appointmentStartTime;
  private LocalTime appointmentEndTime;
  private LocalDateTime createdAt;

  @Enumerated(EnumType.STRING)
  private AppointmentStatusEnum status;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id")
  private DoctorEntity doctor;

  public Long getId() {
    return id;
  }

  public AppointmentEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public AppointmentEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public LocalDate getAppointmentDate() {
    return appointmentDate;
  }

  public AppointmentEntity setAppointmentDate(LocalDate appointmentDate) {
    this.appointmentDate = appointmentDate;
    return this;
  }

  public LocalTime getAppointmentStartTime() {
    return appointmentStartTime;
  }

  public AppointmentEntity setAppointmentStartTime(LocalTime appointmentStartTime) {
    this.appointmentStartTime = appointmentStartTime;
    return this;
  }

  public LocalTime getAppointmentEndTime() {
    return appointmentEndTime;
  }

  public AppointmentEntity setAppointmentEndTime(LocalTime appointmentEndTime) {
    this.appointmentEndTime = appointmentEndTime;
    return this;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public AppointmentEntity setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public AppointmentStatusEnum getStatus() {
    return status;
  }

  public AppointmentEntity setStatus(AppointmentStatusEnum status) {
    this.status = status;
    return this;
  }

  public UserEntity getUser() {
    return user;
  }

  public AppointmentEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }

  public DoctorEntity getDoctor() {
    return doctor;
  }

  public AppointmentEntity setDoctor(DoctorEntity doctor) {
    this.doctor = doctor;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AppointmentEntity that = (AppointmentEntity) o;
    return getId().equals(that.getId())
        && getDescription().equals(that.getDescription())
        && getAppointmentDate().equals(that.getAppointmentDate())
        && getAppointmentStartTime().equals(that.getAppointmentStartTime())
        && getAppointmentEndTime().equals(that.getAppointmentEndTime())
        && getCreatedAt().equals(that.getCreatedAt())
        && getStatus() == that.getStatus()
        && getUser().equals(that.getUser())
        && getDoctor().equals(that.getDoctor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(),
        getDescription(),
        getAppointmentDate(),
        getAppointmentStartTime(),
        getAppointmentEndTime(),
        getCreatedAt(),
        getStatus(),
        getUser(),
        getDoctor());
  }
}
