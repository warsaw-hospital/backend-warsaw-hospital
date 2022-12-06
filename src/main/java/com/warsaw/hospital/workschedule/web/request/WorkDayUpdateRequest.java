package com.warsaw.hospital.workschedule.web.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class WorkDayUpdateRequest {
  private Long id;
  private LocalDate workDate;
  private LocalTime startHour;
  private LocalTime endHour;
  private Boolean isHoliday;
  private Boolean isDayOff;

  public Long getId() {
    return id;
  }

  public WorkDayUpdateRequest setId(Long id) {
    this.id = id;
    return this;
  }

  public LocalDate getWorkDate() {
    return workDate;
  }

  public WorkDayUpdateRequest setWorkDate(LocalDate workDate) {
    this.workDate = workDate;
    return this;
  }

  public LocalTime getStartHour() {
    return startHour;
  }

  public WorkDayUpdateRequest setStartHour(LocalTime startHour) {
    this.startHour = startHour;
    return this;
  }

  public LocalTime getEndHour() {
    return endHour;
  }

  public WorkDayUpdateRequest setEndHour(LocalTime endHour) {
    this.endHour = endHour;
    return this;
  }

  public Boolean getIsHoliday() {
    return isHoliday;
  }

  public WorkDayUpdateRequest setIsHoliday(Boolean holiday) {
    isHoliday = holiday;
    return this;
  }

  public Boolean getIsDayOff() {
    return isDayOff;
  }

  public WorkDayUpdateRequest setIsDayOff(Boolean dayOff) {
    isDayOff = dayOff;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WorkDayUpdateRequest that = (WorkDayUpdateRequest) o;
    return getId().equals(that.getId())
        && getWorkDate().equals(that.getWorkDate())
        && getStartHour().equals(that.getStartHour())
        && getEndHour().equals(that.getEndHour())
        && isHoliday.equals(that.isHoliday)
        && isDayOff.equals(that.isDayOff);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getWorkDate(), getStartHour(), getEndHour(), isHoliday, isDayOff);
  }
}
