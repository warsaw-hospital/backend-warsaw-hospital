package com.warsaw.hospital.workschedule;

import com.opengamma.strata.basics.ReferenceData;
import com.opengamma.strata.basics.date.HolidayCalendar;
import com.warsaw.hospital.exception.ApiException;
import com.warsaw.hospital.workschedule.entity.WorkDayEntity;
import com.warsaw.hospital.workschedule.entity.WorkScheduleTemplateEntity;
import com.warsaw.hospital.workschedule.utils.WorkScheduleUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.opengamma.strata.basics.date.HolidayCalendarIds.PLWA;

@Service
@Transactional
public class WorkScheduleService {
  private final String ERROR_WORK_SCHEDULE_TEMPLATE_MESSAGE_BASE = "err.workScheduleTemplate.";
  private final String ERROR_WORK_DAY_MESSAGE_BASE = "err.workScheduleTemplate.";
  private final WorkScheduleTemplateRepository workScheduleTemplateRepository;
  private final WorkDayRepository workDayRepository;

  public WorkScheduleService(
      WorkScheduleTemplateRepository workScheduleTemplateRepository,
      WorkDayRepository workDayRepository) {
    this.workScheduleTemplateRepository = workScheduleTemplateRepository;
    this.workDayRepository = workDayRepository;
  }

  public List<WorkScheduleTemplateEntity> findAllWorkScheduleTemplatesByDoctorId(Long doctorId) {
    return workScheduleTemplateRepository.findAllByDoctorId(doctorId);
  }

  public WorkScheduleTemplateEntity create(WorkScheduleTemplateEntity entity) {
    if (workScheduleTemplateRepository.existsByDoctorIdAndDayOfWeek(
        entity.getDoctor().getId(), entity.getDayOfWeek())) {
      throw ApiException.conflict(ERROR_WORK_SCHEDULE_TEMPLATE_MESSAGE_BASE + "duplicate")
          .addLabel("doctorId", entity.getDoctor().getId())
          .addLabel("dayOfWeek", entity.getDayOfWeek());
    }
    List<WorkDayEntity> workDays = generateWorkScheduleFromTemplate(entity);
    create(workDays);
    return workScheduleTemplateRepository.save(entity);
  }

  public WorkScheduleTemplateEntity update(WorkScheduleTemplateEntity entity) {
    if (!workScheduleTemplateRepository.existsById(entity.getDoctor().getId())) {
      throw ApiException.notFound(ERROR_WORK_SCHEDULE_TEMPLATE_MESSAGE_BASE + "notFound")
          .addLabel("doctorId", entity.getDoctor().getId())
          .addLabel("dayOfWeek", entity.getDayOfWeek());
    }
    List<WorkDayEntity> workDays = generateWorkScheduleFromTemplate(entity);
    updateWorkDays(workDays, entity.getDoctor().getId());
    return workScheduleTemplateRepository.save(entity);
  }

  public List<WorkScheduleTemplateEntity> update(
      List<WorkScheduleTemplateEntity> entities, Long doctorId) {
    return entities.stream().map(entity -> update(entity, doctorId)).collect(Collectors.toList());
  }

  public WorkScheduleTemplateEntity update(WorkScheduleTemplateEntity entity, Long doctorId) {
    if (!entity.getDoctor().getId().equals(doctorId)) {
      throw ApiException.bad(ERROR_WORK_SCHEDULE_TEMPLATE_MESSAGE_BASE + "wrongDoctor")
          .addLabel("doctorId", entity.getDoctor().getId())
          .addLabel("dayOfWeek", entity.getDayOfWeek());
    }
    return update(entity);
  }

  public void delete(Long id) {
    if (!workScheduleTemplateRepository.existsById(id)) {
      throw ApiException.notFound(ERROR_WORK_SCHEDULE_TEMPLATE_MESSAGE_BASE + "notFound")
          .addLabel("id", id);
    }
    workScheduleTemplateRepository.deleteById(id);
  }

  public void delete(Long id, Long doctorId) {
    WorkScheduleTemplateEntity entity =
        workScheduleTemplateRepository
            .findById(id)
            .orElseThrow(
                () ->
                    ApiException.notFound(ERROR_WORK_SCHEDULE_TEMPLATE_MESSAGE_BASE + "notFound")
                        .addLabel("id", id));

    if (!entity.getDoctor().getId().equals(doctorId)) {
      throw ApiException.bad(ERROR_WORK_SCHEDULE_TEMPLATE_MESSAGE_BASE + "wrongDoctor")
          .addLabel("doctorId", entity.getId());
    }
    workScheduleTemplateRepository.deleteById(id);
  }

  public List<WorkDayEntity> findAllWorkDaysByDoctorId(Long doctorId) {
    return workDayRepository.findAllByDoctorId(doctorId);
  }

  public WorkDayEntity create(WorkDayEntity entity) {
    if (workDayRepository.existsByWorkDateAndDoctorId(
        entity.getWorkDate(), entity.getDoctor().getId())) {
      throw ApiException.conflict(ERROR_WORK_DAY_MESSAGE_BASE + "duplicate")
          .addLabel("workDate", entity.getWorkDate())
          .addLabel("doctorId", entity.getDoctor().getId());
    }

    return workDayRepository.save(entity);
  }

  public List<WorkDayEntity> create(List<WorkDayEntity> entities) {
    return entities.stream().map(this::create).collect(Collectors.toList());
  }

  public WorkDayEntity update(WorkDayEntity entity) {
    if (!workDayRepository.existsById(entity.getId())) {
      throw ApiException.notFound(ERROR_WORK_DAY_MESSAGE_BASE + "notFound")
          .addLabel("id", entity.getId());
    }
    return workDayRepository.save(entity);
  }

  public WorkDayEntity update(WorkDayEntity entity, Long doctorId) {
    if (!entity.getDoctor().getId().equals(doctorId)) {
      throw ApiException.bad(ERROR_WORK_DAY_MESSAGE_BASE + "wrongDoctor")
          .addLabel("doctorId", entity.getDoctor().getId());
    }
    return update(entity);
  }

  public List<WorkDayEntity> updateWorkDays(List<WorkDayEntity> entities, Long doctorId) {
    return entities.stream().map(entity -> update(entity, doctorId)).collect(Collectors.toList());
  }

  private List<WorkDayEntity> generateWorkScheduleFromTemplate(
      WorkScheduleTemplateEntity workSchedule) {
    HolidayCalendar holidayCalendar = PLWA.resolve(ReferenceData.standard());
    List<LocalDate> workDates =
        WorkScheduleUtil.getAllDatesByWeekDayThreeMonthsFromNow(workSchedule.getDayOfWeek());

    return workDates.stream()
        .map(
            workDate ->
                new WorkDayEntity()
                    .setWorkDate(workDate)
                    .setStartHour(workSchedule.getStartHour())
                    .setEndHour(workSchedule.getEndHour())
                    .setIsHoliday(holidayCalendar.isHoliday(workDate))
                    .setIsDayOff(false)
                    .setDoctor(workSchedule.getDoctor()))
        .collect(Collectors.toList());
  }
}
