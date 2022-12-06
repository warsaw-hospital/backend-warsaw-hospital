package com.warsaw.hospital.workschedule.web.response;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class WorkScheduleTemplateResponse {
  private Long id;
  private DayOfWeek dayOfWeek;
  private LocalTime startHour;
  private LocalTime endHour;
  private Boolean isDayOff;

  public Long getId() {
    return id;
  }

  public WorkScheduleTemplateResponse setId(Long id) {
    this.id = id;
    return this;
  }

  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }

  public WorkScheduleTemplateResponse setDayOfWeek(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
    return this;
  }

  public LocalTime getStartHour() {
    return startHour;
  }

  public WorkScheduleTemplateResponse setStartHour(LocalTime startHour) {
    this.startHour = startHour;
    return this;
  }

  public LocalTime getEndHour() {
    return endHour;
  }

  public WorkScheduleTemplateResponse setEndHour(LocalTime endHour) {
    this.endHour = endHour;
    return this;
  }

  public Boolean getIsDayOff() {
    return isDayOff;
  }

  public WorkScheduleTemplateResponse setIsDayOff(Boolean dayOff) {
    isDayOff = dayOff;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WorkScheduleTemplateResponse that = (WorkScheduleTemplateResponse) o;
    return getId().equals(that.getId())
        && getDayOfWeek().equals(that.getDayOfWeek())
        && getStartHour().equals(that.getStartHour())
        && getEndHour().equals(that.getEndHour())
        && getIsDayOff().equals(that.getIsDayOff());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getDayOfWeek(), getStartHour(), getEndHour(), getIsDayOff());
  }
}
