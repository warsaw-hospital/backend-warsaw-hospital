package com.warsaw.hospital.workschedule.mapper;

import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.workschedule.entity.WorkDayEntity;
import com.warsaw.hospital.workschedule.entity.WorkScheduleTemplateEntity;
import com.warsaw.hospital.workschedule.web.request.WorkDayUpdateRequest;
import com.warsaw.hospital.workschedule.web.request.WorkScheduleTemplateCreateRequest;
import com.warsaw.hospital.workschedule.web.request.WorkScheduleTemplateUpdateRequest;
import com.warsaw.hospital.workschedule.web.response.WorkDayResponse;
import com.warsaw.hospital.workschedule.web.response.WorkScheduleTemplateResponse;

import java.util.List;
import java.util.stream.Collectors;

public class WorkScheduleMapper {
  public static WorkScheduleTemplateResponse toWorkScheduleTemplateResponse(
      WorkScheduleTemplateEntity entity) {
    return new WorkScheduleTemplateResponse()
        .setId(entity.getId())
        .setDayOfWeek(entity.getDayOfWeek())
        .setStartHour(entity.getStartHour())
        .setEndHour(entity.getEndHour())
        .setIsDayOff(entity.getIsDayOff());
  }

  public static List<WorkScheduleTemplateResponse> toWorkScheduleTemplateResponses(
      List<WorkScheduleTemplateEntity> entities) {
    return entities.stream()
        .map(WorkScheduleMapper::toWorkScheduleTemplateResponse)
        .collect(Collectors.toList());
  }

  public static WorkScheduleTemplateEntity toWorkScheduleTemplateEntity(
      WorkScheduleTemplateCreateRequest request, Long doctorId) {
    return new WorkScheduleTemplateEntity()
        .setDayOfWeek(request.getDayOfWeek())
        .setStartHour(request.getStartHour())
        .setEndHour(request.getEndHour())
        .setIsDayOff(request.getIsDayOff())
        .setDoctor(new DoctorEntity().setId(doctorId));
  }

  public static WorkScheduleTemplateEntity toWorkScheduleTemplateEntity(
      WorkScheduleTemplateUpdateRequest request, Long doctorId) {
    return new WorkScheduleTemplateEntity()
        .setId(request.getId())
        .setDayOfWeek(request.getDayOfWeek())
        .setStartHour(request.getStartHour())
        .setEndHour(request.getEndHour())
        .setIsDayOff(request.getIsDayOff())
        .setDoctor(new DoctorEntity().setId(doctorId));
  }

  public static WorkDayEntity toWorkDayEntity(WorkDayUpdateRequest request, Long doctorId) {
    return new WorkDayEntity()
        .setId(request.getId())
        .setWorkDate(request.getWorkDate())
        .setStartHour(request.getStartHour())
        .setEndHour(request.getEndHour())
        .setIsDayOff(request.getIsDayOff())
        .setIsHoliday(request.getIsHoliday())
        .setDoctor(new DoctorEntity().setId(doctorId));
  }

  public static List<WorkDayEntity> toWorkDayEntities(
      List<WorkDayUpdateRequest> requests, Long doctorId) {
    return requests.stream()
        .map(request -> toWorkDayEntity(request, doctorId))
        .collect(Collectors.toList());
  }

  public static WorkDayResponse toWorkDayResponse(WorkDayEntity entity) {
    return new WorkDayResponse()
        .setId(entity.getId())
        .setWorkDate(entity.getWorkDate())
        .setStartHour(entity.getStartHour())
        .setEndHour(entity.getEndHour())
        .setIsHoliday(entity.getIsHoliday())
        .setIsDayOff(entity.getIsDayOff());
  }

  public static List<WorkDayResponse> toWorkDayResponses(List<WorkDayEntity> entities) {
    return entities.stream()
        .map(WorkScheduleMapper::toWorkDayResponse)
        .collect(Collectors.toList());
  }
}
