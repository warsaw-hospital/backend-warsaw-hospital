package com.warsaw.hospital.workschedule.web.entry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class HolidayRequestEntry {
  private String name;
  private String description;
  private LocalDate fromDate;
  private LocalDate toDate;
  private LocalDateTime requestedAt;

  public String getName() {
    return name;
  }

  public HolidayRequestEntry setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public HolidayRequestEntry setDescription(String description) {
    this.description = description;
    return this;
  }

  public LocalDate getFromDate() {
    return fromDate;
  }

  public HolidayRequestEntry setFromDate(LocalDate fromDate) {
    this.fromDate = fromDate;
    return this;
  }

  public LocalDate getToDate() {
    return toDate;
  }

  public HolidayRequestEntry setToDate(LocalDate toDate) {
    this.toDate = toDate;
    return this;
  }

  public LocalDateTime getRequestedAt() {
    return requestedAt;
  }

  public HolidayRequestEntry setRequestedAt(LocalDateTime requestedAt) {
    this.requestedAt = requestedAt;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HolidayRequestEntry that = (HolidayRequestEntry) o;
    return getName().equals(that.getName())
        && getDescription().equals(that.getDescription())
        && getFromDate().equals(that.getFromDate())
        && getToDate().equals(that.getToDate())
        && getRequestedAt().equals(that.getRequestedAt());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getDescription(), getFromDate(), getToDate(), getRequestedAt());
  }
}
