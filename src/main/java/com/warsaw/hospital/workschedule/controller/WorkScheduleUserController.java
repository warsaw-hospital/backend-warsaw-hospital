package com.warsaw.hospital.workschedule.controller;

import com.warsaw.hospital.workschedule.WorkScheduleService;
import com.warsaw.hospital.workschedule.entity.WorkDayEntity;
import com.warsaw.hospital.workschedule.mapper.WorkScheduleMapper;
import com.warsaw.hospital.workschedule.web.response.WorkDayResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/work-schedule")
public class WorkScheduleUserController {
  private final WorkScheduleService service;

  public WorkScheduleUserController(WorkScheduleService service) {
    this.service = service;
  }

  @GetMapping("/work-day/doctor/{doctorId}")
  public List<WorkDayResponse> findAllWorkDaysByDoctorId(@PathVariable Long doctorId) {
    List<WorkDayEntity> entities = service.findAllWorkDaysByDoctorId(doctorId);
    return WorkScheduleMapper.toWorkDayResponses(entities);
  }

  @GetMapping("/work-day/{id}")
  public WorkDayResponse findWorkDayById(@PathVariable Long id) {
    WorkDayEntity entity = service.findWorkDayById(id);
    return WorkScheduleMapper.toWorkDayResponse(entity);
  }
}
