package com.warsaw.hospital.appointment.web.response;

import com.warsaw.hospital.appointment.entity.enums.AppointmentStatusEnum;
import com.warsaw.hospital.doctor.web.response.DoctorResponse;
import com.warsaw.hospital.user.web.response.FullUserInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class AppointmentResponse {
  private Long id;
  private String description;
  private LocalDate appointmentDate;
  private LocalTime appointmentStartTime;
  private LocalTime appointmentEndTime;
  private LocalDateTime createdAt;
  private AppointmentStatusEnum status;
  private FullUserInfo user;
  private DoctorResponse doctor;

  public Long getId() {
    return id;
  }

  public AppointmentResponse setId(Long id) {
    this.id = id;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public AppointmentResponse setDescription(String description) {
    this.description = description;
    return this;
  }

  public LocalDate getAppointmentDate() {
    return appointmentDate;
  }

  public AppointmentResponse setAppointmentDate(LocalDate appointmentDate) {
    this.appointmentDate = appointmentDate;
    return this;
  }

  public LocalTime getAppointmentStartTime() {
    return appointmentStartTime;
  }

  public AppointmentResponse setAppointmentStartTime(LocalTime appointmentStartTime) {
    this.appointmentStartTime = appointmentStartTime;
    return this;
  }

  public LocalTime getAppointmentEndTime() {
    return appointmentEndTime;
  }

  public AppointmentResponse setAppointmentEndTime(LocalTime appointmentEndTime) {
    this.appointmentEndTime = appointmentEndTime;
    return this;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public AppointmentResponse setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public AppointmentStatusEnum getStatus() {
    return status;
  }

  public AppointmentResponse setStatus(AppointmentStatusEnum status) {
    this.status = status;
    return this;
  }

  public FullUserInfo getUser() {
    return user;
  }

  public AppointmentResponse setUser(FullUserInfo user) {
    this.user = user;
    return this;
  }

  public DoctorResponse getDoctor() {
    return doctor;
  }

  public AppointmentResponse setDoctor(DoctorResponse doctor) {
    this.doctor = doctor;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AppointmentResponse that = (AppointmentResponse) o;
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
