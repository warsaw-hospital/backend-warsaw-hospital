package com.warsaw.hospital.doctor.controller;

import com.warsaw.hospital.auth.config.AuthenticatedProfile;
import com.warsaw.hospital.doctor.DoctorService;
import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.doctor.mapper.DoctorMapper;
import com.warsaw.hospital.doctor.web.request.DoctorCreateRequest;
import com.warsaw.hospital.doctor.web.request.DoctorUpdateRequest;
import com.warsaw.hospital.doctor.web.response.DoctorResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/doctor")
public class DoctorAdminController {
  private final DoctorService service;

  public DoctorAdminController(DoctorService service) {
    this.service = service;
  }

  @PostMapping
  @Operation(summary = "Create a doctor entity")
  public DoctorResponse create(
      @RequestBody DoctorCreateRequest request, AuthenticatedProfile profile) {
    DoctorEntity entity = service.create(DoctorMapper.toEntity(request));
    return DoctorMapper.toResponse(entity);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a doctor entity")
  public DoctorResponse update(
      @PathVariable Long id,
      @RequestBody DoctorUpdateRequest request,
      AuthenticatedProfile profile) {
    DoctorEntity entity = service.update(DoctorMapper.toEntity(request));
    return DoctorMapper.toResponse(entity);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a doctor entity")
  public void delete(@PathVariable Long id, AuthenticatedProfile profile) {
    service.delete(id);
  }
}
