package com.warsaw.hospital.appointment.controller;

import com.warsaw.hospital.appointment.AppointmentService;
import com.warsaw.hospital.appointment.entity.AppointmentEntity;
import com.warsaw.hospital.appointment.entity.enums.AppointmentStatusEnum;
import com.warsaw.hospital.appointment.mapper.AppointmentMapper;
import com.warsaw.hospital.appointment.web.request.AppointmentDoctorCreateRequest;
import com.warsaw.hospital.appointment.web.request.AppointmentDoctorUpdateRequest;
import com.warsaw.hospital.appointment.web.response.AppointmentResponse;
import com.warsaw.hospital.auth.config.AuthenticatedProfile;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/v1/appointment")
public class AppointmentAdminController {
  private final AppointmentService appointmentService;

  public AppointmentAdminController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @GetMapping("/filter")
  @Operation(summary = "Get all appointments filtered by status and userId.")
  public List<AppointmentResponse> findAllFilteredBy(
      @RequestParam(required = false) AppointmentStatusEnum status, AuthenticatedProfile profile) {
    return AppointmentMapper.toResponses(
        appointmentService.findAllFilteredBy(status, null, profile.getUserId()));
  }

  @PostMapping
  @Operation(summary = "Create a new appointment.")
  public AppointmentResponse create(
      @RequestBody AppointmentDoctorCreateRequest request, AuthenticatedProfile profile) {
    AppointmentEntity entity = AppointmentMapper.toEntityDoctor(request);
    return AppointmentMapper.toResponse(appointmentService.doctorCreate(entity, profile.getId()));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update an appointment.")
  public AppointmentResponse update(
      @PathVariable Long id,
      @RequestBody AppointmentDoctorUpdateRequest request,
      AuthenticatedProfile profile) {
    AppointmentEntity entity = AppointmentMapper.toEntityDoctor(request);
    return AppointmentMapper.toResponse(appointmentService.doctorUpdate(entity, profile.getId()));
  }
}
