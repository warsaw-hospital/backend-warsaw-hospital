package com.warsaw.hospital.workschedule.controller;

import com.warsaw.hospital.auth.config.AuthenticatedProfile;
import com.warsaw.hospital.workschedule.WorkScheduleService;
import com.warsaw.hospital.workschedule.entity.WorkDayEntity;
import com.warsaw.hospital.workschedule.entity.WorkScheduleTemplateEntity;
import com.warsaw.hospital.workschedule.mapper.WorkScheduleMapper;
import com.warsaw.hospital.workschedule.web.request.WorkDayUpdateRequest;
import com.warsaw.hospital.workschedule.web.request.WorkScheduleTemplateCreateRequest;
import com.warsaw.hospital.workschedule.web.request.WorkScheduleTemplateUpdateRequest;
import com.warsaw.hospital.workschedule.web.response.WorkDayResponse;
import com.warsaw.hospital.workschedule.web.response.WorkScheduleTemplateResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/work-schedule")
public class WorkScheduleAdminController {

  private final WorkScheduleService service;

  public WorkScheduleAdminController(WorkScheduleService service) {
    this.service = service;
  }

  @GetMapping("/template")
  public List<WorkScheduleTemplateResponse> findAllByDoctorId(AuthenticatedProfile profile) {
    List<WorkScheduleTemplateEntity> entities =
        service.findAllWorkScheduleTemplatesByDoctorId(profile.getId());
    return WorkScheduleMapper.toWorkScheduleTemplateResponses(entities);
  }

  @GetMapping("/template/{id}")
  public WorkDayResponse findWorkScheduleTemplateById(@PathVariable Long id) {
    WorkDayEntity entity = service.findWorkDayById(id);
    return WorkScheduleMapper.toWorkDayResponse(entity);
  }

  @PostMapping("/template")
  public WorkScheduleTemplateResponse create(
      @RequestBody WorkScheduleTemplateCreateRequest request, AuthenticatedProfile profile) {
    WorkScheduleTemplateEntity entity =
        WorkScheduleMapper.toWorkScheduleTemplateEntity(request, profile.getId());

    return WorkScheduleMapper.toWorkScheduleTemplateResponse(service.create(entity));
  }

  @PutMapping("/template/{id}")
  public WorkScheduleTemplateResponse update(
      @PathVariable Long id,
      @RequestBody WorkScheduleTemplateUpdateRequest request,
      AuthenticatedProfile profile) {
    WorkScheduleTemplateEntity entity =
        WorkScheduleMapper.toWorkScheduleTemplateEntity(request, profile.getId());

    return WorkScheduleMapper.toWorkScheduleTemplateResponse(service.update(entity));
  }

  @DeleteMapping("/template/{id}")
  public void delete(@PathVariable Long id, AuthenticatedProfile profile) {
    service.delete(id, profile.getId());
  }

  @GetMapping("/work-day")
  public List<WorkDayResponse> findAllWorkDaysByDoctorId(AuthenticatedProfile profile) {
    List<WorkDayEntity> entities = service.findAllWorkDaysByDoctorId(profile.getId());
    return WorkScheduleMapper.toWorkDayResponses(entities);
  }

  @PutMapping("/work-day/{id}")
  public WorkDayResponse updateWorkDay(WorkDayUpdateRequest request, AuthenticatedProfile profile) {
    WorkDayEntity entity = WorkScheduleMapper.toWorkDayEntity(request, profile.getId());
    return WorkScheduleMapper.toWorkDayResponse(service.update(entity, profile.getId()));
  }
}
