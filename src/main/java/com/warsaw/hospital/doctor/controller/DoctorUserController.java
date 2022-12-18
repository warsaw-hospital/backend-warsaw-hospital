package com.warsaw.hospital.doctor.controller;

import com.warsaw.hospital.doctor.DoctorService;
import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.doctor.enums.DoctorSpecializationEnum;
import com.warsaw.hospital.doctor.mapper.DoctorMapper;
import com.warsaw.hospital.doctor.web.response.DoctorResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorUserController {
  private final DoctorService service;

  public DoctorUserController(DoctorService service) {
    this.service = service;
  }

  @GetMapping("/filter")
  @Operation(summary = "Get all doctors filtered by.")
  public List<DoctorResponse> findAllFilteredBy(
      @RequestParam(required = false) String search,
      @RequestParam(required = false) DoctorSpecializationEnum specialization) {
    return DoctorMapper.toResponses(service.findAllFilteredBy(search, specialization));
  }

  @Operation(summary = "Get a doctors by id.")
  @GetMapping("/{id}")
  public DoctorResponse findById(@PathVariable Long id) {
    DoctorEntity entity = service.findById(id);
    return DoctorMapper.toResponse(entity);
  }
}
