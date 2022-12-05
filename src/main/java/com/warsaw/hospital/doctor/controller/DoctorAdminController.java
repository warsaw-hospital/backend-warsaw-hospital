package com.warsaw.hospital.doctor.controller;

import com.warsaw.hospital.doctor.DoctorService;
import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.doctor.mapper.DoctorMapper;
import com.warsaw.hospital.doctor.web.request.DoctorCreateRequest;
import com.warsaw.hospital.doctor.web.response.DoctorResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/doctor")
public class DoctorAdminController {
  private final DoctorService service;

  public DoctorAdminController(DoctorService service) {
    this.service = service;
  }

  public DoctorResponse create(@RequestBody DoctorCreateRequest request) {
    DoctorEntity entity = service.create(DoctorMapper.toEntity(request));
    return DoctorMapper.toResponse(entity);
  }
}
