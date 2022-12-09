package com.warsaw.hospital.appointment.web.request;

import com.warsaw.hospital.appointment.entity.enums.AppointmentStatusEnum;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class
AppointmentDoctorUpdateRequest {
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

  @NotNull(message = "userId is ${validatedValue}, but it must not be null")
  private Long userId;

  public Long getId() {
    return id;
  }

  public AppointmentDoctorUpdateRequest setId(Long id) {
    this.id = id;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public AppointmentDoctorUpdateRequest setDescription(String description) {
    this.description = description;
    return this;
  }

  public LocalDate getAppointmentDate() {
    return appointmentDate;
  }

  public AppointmentDoctorUpdateRequest setAppointmentDate(LocalDate appointmentDate) {
    this.appointmentDate = appointmentDate;
    return this;
  }

  public LocalTime getAppointmentStartTime() {
    return appointmentStartTime;
  }

  public AppointmentDoctorUpdateRequest setAppointmentStartTime(LocalTime appointmentStartTime) {
    this.appointmentStartTime = appointmentStartTime;
    return this;
  }

  public LocalTime getAppointmentEndTime() {
    return appointmentEndTime;
  }

  public AppointmentDoctorUpdateRequest setAppointmentEndTime(LocalTime appointmentEndTime) {
    this.appointmentEndTime = appointmentEndTime;
    return this;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public AppointmentDoctorUpdateRequest setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  public AppointmentStatusEnum getStatus() {
    return status;
  }

  public AppointmentDoctorUpdateRequest setStatus(AppointmentStatusEnum status) {
    this.status = status;
    return this;
  }

  public Long getUserId() {
    return userId;
  }

  public AppointmentDoctorUpdateRequest setUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AppointmentDoctorUpdateRequest that = (AppointmentDoctorUpdateRequest) o;
    return getId().equals(that.getId())
        && getDescription().equals(that.getDescription())
        && getAppointmentDate().equals(that.getAppointmentDate())
        && getAppointmentStartTime().equals(that.getAppointmentStartTime())
        && getAppointmentEndTime().equals(that.getAppointmentEndTime())
        && getCreatedAt().equals(that.getCreatedAt())
        && getStatus() == that.getStatus()
        && getUserId().equals(that.getUserId());
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
        getUserId());
  }
}
