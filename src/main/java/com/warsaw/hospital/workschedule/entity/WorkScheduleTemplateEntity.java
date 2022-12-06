package com.warsaw.hospital.workschedule.entity;

import com.warsaw.hospital.doctor.entity.DoctorEntity;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Entity(name = "work_schedule_template")
public class WorkScheduleTemplateEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "day_of_week")
  private DayOfWeek dayOfWeek;

  private LocalTime startHour;
  private LocalTime endHour;
  private Boolean isDayOff;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id", referencedColumnName = "id")
  private DoctorEntity doctor;

  public Long getId() {
    return id;
  }

  public WorkScheduleTemplateEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }

  public WorkScheduleTemplateEntity setDayOfWeek(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
    return this;
  }

  public LocalTime getStartHour() {
    return startHour;
  }

  public WorkScheduleTemplateEntity setStartHour(LocalTime startHour) {
    this.startHour = startHour;
    return this;
  }

  public LocalTime getEndHour() {
    return endHour;
  }

  public WorkScheduleTemplateEntity setEndHour(LocalTime endHour) {
    this.endHour = endHour;
    return this;
  }

  public Boolean getIsDayOff() {
    return isDayOff;
  }

  public WorkScheduleTemplateEntity setIsDayOff(Boolean dayOff) {
    isDayOff = dayOff;
    return this;
  }

  public DoctorEntity getDoctor() {
    return doctor;
  }

  public WorkScheduleTemplateEntity setDoctor(DoctorEntity doctor) {
    this.doctor = doctor;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WorkScheduleTemplateEntity that = (WorkScheduleTemplateEntity) o;
    return getId().equals(that.getId())
        && getDayOfWeek() == that.getDayOfWeek()
        && getStartHour().equals(that.getStartHour())
        && getEndHour().equals(that.getEndHour())
        && isDayOff.equals(that.isDayOff)
        && getDoctor().equals(that.getDoctor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(), getDayOfWeek(), getStartHour(), getEndHour(), isDayOff, getDoctor());
  }
}
