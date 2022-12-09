package com.warsaw.hospital.appointment.controller;

import com.warsaw.hospital.appointment.AppointmentService;
import com.warsaw.hospital.appointment.entity.AppointmentEntity;
import com.warsaw.hospital.appointment.entity.enums.AppointmentStatusEnum;
import com.warsaw.hospital.appointment.mapper.AppointmentMapper;
import com.warsaw.hospital.appointment.web.request.AppointmentUserCreateRequest;
import com.warsaw.hospital.appointment.web.request.AppointmentUserUpdateRequest;
import com.warsaw.hospital.appointment.web.response.AppointmentResponse;
import com.warsaw.hospital.auth.config.AuthenticatedProfile;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/appointment")
public class AppointmentUserController {
  private final AppointmentService appointmentService;

  public AppointmentUserController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @GetMapping("/filter")
  @Operation(summary = "Get all appointments filtered by status and userId.")
  public List<AppointmentResponse> findAllFilteredBy(
      @RequestParam(required = false) AppointmentStatusEnum status, AuthenticatedProfile profile) {
    return AppointmentMapper.toResponses(
        appointmentService.findAllFilteredBy(status, profile.getUserId(), null));
  }

  @PostMapping
  @Operation(summary = "Create a new appointment.")
  public AppointmentResponse create(
      @RequestBody @Valid AppointmentUserCreateRequest request, AuthenticatedProfile profile) {
    AppointmentEntity entity = AppointmentMapper.toEntityUser(request);
    return AppointmentMapper.toResponse(appointmentService.userCreate(entity, profile.getId()));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update an appointment.")
  public AppointmentResponse update(
      @PathVariable Long id,
      @RequestBody AppointmentUserUpdateRequest request,
      AuthenticatedProfile profile) {
    AppointmentEntity entity = AppointmentMapper.toEntityUser(request);
    return AppointmentMapper.toResponse(appointmentService.userUpdate(entity, profile.getId()));
  }
}
