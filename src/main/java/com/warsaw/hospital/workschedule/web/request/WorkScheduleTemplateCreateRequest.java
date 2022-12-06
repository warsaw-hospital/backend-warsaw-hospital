package com.warsaw.hospital.workschedule.web.request;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class WorkScheduleTemplateCreateRequest {
  @NotNull(message = "Day of week is ${validatedValue}, but it must not be null")
  private DayOfWeek dayOfWeek;

  @NotNull(message = "Start hour is ${validatedValue}, but it must not be null")
  private LocalTime startHour;

  @NotNull(message = "End hour is ${validatedValue}, but it must not be null")
  private LocalTime endHour;

  @NotNull(message = "Is day off is ${validatedValue}, but it must not be null")
  private Boolean isDayOff;

  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }

  public WorkScheduleTemplateCreateRequest setDayOfWeek(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
    return this;
  }

  public LocalTime getStartHour() {
    return startHour;
  }

  public WorkScheduleTemplateCreateRequest setStartHour(LocalTime startHour) {
    this.startHour = startHour;
    return this;
  }

  public LocalTime getEndHour() {
    return endHour;
  }

  public WorkScheduleTemplateCreateRequest setEndHour(LocalTime endHour) {
    this.endHour = endHour;
    return this;
  }

  public Boolean getIsDayOff() {
    return isDayOff;
  }

  public WorkScheduleTemplateCreateRequest setIsDayOff(Boolean dayOff) {
    isDayOff = dayOff;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WorkScheduleTemplateCreateRequest that = (WorkScheduleTemplateCreateRequest) o;
    return getDayOfWeek() == that.getDayOfWeek()
        && getStartHour().equals(that.getStartHour())
        && getEndHour().equals(that.getEndHour())
        && isDayOff.equals(that.isDayOff);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDayOfWeek(), getStartHour(), getEndHour(), isDayOff);
  }
}
