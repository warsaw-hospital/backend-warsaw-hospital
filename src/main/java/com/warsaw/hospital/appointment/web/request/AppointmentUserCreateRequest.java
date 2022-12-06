package com.warsaw.hospital.appointment.web.request;

import com.warsaw.hospital.appointment.entity.enums.AppointmentStatusEnum;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class AppointmentUserCreateRequest {

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
  private Long doctorId;

  public String getDescription() {
    return description;
  }

  public AppointmentUserCreateRequest setDescription(String description) {
    this.description = description;
    return this;
  }

  public LocalDate getAppointmentDate() {
    return appointmentDate;
  }

  public AppointmentUserCreateRequest setAppointmentDate(LocalDate appointmentDate) {
    this.appointmentDate = appointmentDate;
    return this;
  }

  public LocalTime getAppointmentStartTime() {
    return appointmentStartTime;
  }

  public AppointmentUserCreateRequest setAppointmentStartTime(LocalTime appointmentStartTime) {
    this.appointmentStartTime = appointmentStartTime;
    return this;
  }

  public LocalTime getAppointmentEndTime() {
    return appointmentEndTime;
  }

  public AppointmentUserCreateRequest setAppointmentEndTime(LocalTime appointmentEndTime) {
    this.appointmentEndTime = appointmentEndTime;
    return this;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public AppointmentUserCreateRequest setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public AppointmentStatusEnum getStatus() {
    return status;
  }

  public AppointmentUserCreateRequest setStatus(AppointmentStatusEnum status) {
    this.status = status;
    return this;
  }

  public Long getDoctorId() {
    return doctorId;
  }

  public AppointmentUserCreateRequest setDoctorId(Long doctorId) {
    this.doctorId = doctorId;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AppointmentUserCreateRequest that = (AppointmentUserCreateRequest) o;
    return getDescription().equals(that.getDescription())
        && getAppointmentDate().equals(that.getAppointmentDate())
        && getAppointmentStartTime().equals(that.getAppointmentStartTime())
        && getAppointmentEndTime().equals(that.getAppointmentEndTime())
        && getCreatedAt().equals(that.getCreatedAt())
        && getStatus() == that.getStatus()
        && getDoctorId().equals(that.getDoctorId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getDescription(),
        getAppointmentDate(),
        getAppointmentStartTime(),
        getAppointmentEndTime(),
        getCreatedAt(),
        getStatus(),
        getDoctorId());
  }
}
