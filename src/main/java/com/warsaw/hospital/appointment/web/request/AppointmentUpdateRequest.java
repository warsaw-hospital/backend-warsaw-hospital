package com.warsaw.hospital.appointment.web.request;

import com.warsaw.hospital.appointment.entity.enums.AppointmentStatusEnum;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class AppointmentUpdateRequest {
  @NotNull(message = "id is ${validatedValue}, but it must not be null")
  private Long id;

  @NotEmpty(message = "description is ${validatedValue}, but it must not be null")
  private String description;

  @NotNull(message = "appointmentDate is ${validatedValue}, but it must not be null")
  @FutureOrPresent(
      message =
          "appointmentDate is ${validatedValue}, but the date must be in the present or fitire")
  private LocalDate appointmentDate;

  @NotNull(message = "appointmentStartTime is ${validatedValue}, but it must not be null")
  private LocalTime appointmentStartTime;

  @NotNull(message = "appointmentEndTime is ${validatedValue}, but it must not be null")
  private LocalTime appointmentEndTime;

  @NotNull(message = "createdAt is ${validatedValue}, but it must not be null")
  private LocalDateTime createdAt;

  @NotNull(message = "status is ${validatedValue}, but it must not be null")
  private AppointmentStatusEnum status;

  @NotNull(message = "doctorId is ${validatedValue}, but it must not be null")
  private Long userId;

  @NotNull(message = "doctorId is ${validatedValue}, but it must not be null")
  private Long doctorId;

  public Long getId() {
    return id;
  }

  public AppointmentUpdateRequest setId(Long id) {
    this.id = id;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public AppointmentUpdateRequest setDescription(String description) {
    this.description = description;
    return this;
  }

  public LocalDate getAppointmentDate() {
    return appointmentDate;
  }

  public AppointmentUpdateRequest setAppointmentDate(LocalDate appointmentDate) {
    this.appointmentDate = appointmentDate;
    return this;
  }

  public LocalTime getAppointmentStartTime() {
    return appointmentStartTime;
  }

  public AppointmentUpdateRequest setAppointmentStartTime(LocalTime appointmentStartTime) {
    this.appointmentStartTime = appointmentStartTime;
    return this;
  }

  public LocalTime getAppointmentEndTime() {
    return appointmentEndTime;
  }

  public AppointmentUpdateRequest setAppointmentEndTime(LocalTime appointmentEndTime) {
    this.appointmentEndTime = appointmentEndTime;
    return this;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public AppointmentUpdateRequest setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public AppointmentStatusEnum getStatus() {
    return status;
  }

  public AppointmentUpdateRequest setStatus(AppointmentStatusEnum status) {
    this.status = status;
    return this;
  }

  public Long getUserId() {
    return userId;
  }

  public AppointmentUpdateRequest setUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  public Long getDoctorId() {
    return doctorId;
  }

  public AppointmentUpdateRequest setDoctorId(Long doctorId) {
    this.doctorId = doctorId;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AppointmentUpdateRequest that = (AppointmentUpdateRequest) o;
    return getId().equals(that.getId())
        && getDescription().equals(that.getDescription())
        && getAppointmentDate().equals(that.getAppointmentDate())
        && getAppointmentStartTime().equals(that.getAppointmentStartTime())
        && getAppointmentEndTime().equals(that.getAppointmentEndTime())
        && getCreatedAt().equals(that.getCreatedAt())
        && getStatus() == that.getStatus()
        && getUserId().equals(that.getUserId())
        && getDoctorId().equals(that.getDoctorId());
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
        getUserId(),
        getDoctorId());
  }
}
