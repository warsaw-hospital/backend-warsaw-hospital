package com.warsaw.hospital.workschedule.entity;

import com.warsaw.hospital.doctor.entity.DoctorEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity(name = "work_day")
public class WorkDayEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate workDate;
  private LocalTime startHour;
  private LocalTime endHour;
  private Boolean isHoliday;
  private Boolean isDayOff;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id", referencedColumnName = "id")
  private DoctorEntity doctor;

  public Long getId() {
    return id;
  }

  public WorkDayEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public LocalDate getWorkDate() {
    return workDate;
  }

  public WorkDayEntity setWorkDate(LocalDate workDate) {
    this.workDate = workDate;
    return this;
  }

  public LocalTime getStartHour() {
    return startHour;
  }

  public WorkDayEntity setStartHour(LocalTime startHour) {
    this.startHour = startHour;
    return this;
  }

  public LocalTime getEndHour() {
    return endHour;
  }

  public WorkDayEntity setEndHour(LocalTime endHour) {
    this.endHour = endHour;
    return this;
  }

  public Boolean getIsHoliday() {
    return isHoliday;
  }

  public WorkDayEntity setIsHoliday(Boolean holiday) {
    isHoliday = holiday;
    return this;
  }

  public Boolean getIsDayOff() {
    return isDayOff;
  }

  public WorkDayEntity setIsDayOff(Boolean dayOff) {
    isDayOff = dayOff;
    return this;
  }

  public DoctorEntity getDoctor() {
    return doctor;
  }

  public WorkDayEntity setDoctor(DoctorEntity doctor) {
    this.doctor = doctor;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WorkDayEntity that = (WorkDayEntity) o;
    return getId().equals(that.getId())
        && getWorkDate().equals(that.getWorkDate())
        && getStartHour().equals(that.getStartHour())
        && getEndHour().equals(that.getEndHour())
        && isHoliday.equals(that.isHoliday)
        && isDayOff.equals(that.isDayOff)
        && getDoctor().equals(that.getDoctor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(), getWorkDate(), getStartHour(), getEndHour(), isHoliday, isDayOff, getDoctor());
  }
}
