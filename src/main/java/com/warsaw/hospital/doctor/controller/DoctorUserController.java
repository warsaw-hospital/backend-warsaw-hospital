package com.warsaw.hospital.doctor.controller;

import com.warsaw.hospital.doctor.DoctorService;
import com.warsaw.hospital.doctor.DoctorSpecializationService;
import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.doctor.enums.DoctorSpecializationEnum;
import com.warsaw.hospital.doctor.mapper.DoctorMapper;
import com.warsaw.hospital.doctor.mapper.DoctorSpecializationMapper;
import com.warsaw.hospital.doctor.web.response.DoctorResponse;
import com.warsaw.hospital.doctor.web.response.DoctorSpecializationResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorUserController {
  private final DoctorService service;
  private final DoctorSpecializationService specializationService;

  public DoctorUserController(
      DoctorService service, DoctorSpecializationService specializationService) {
    this.service = service;
    this.specializationService = specializationService;
  }

  @Operation(summary = "Get all doctors filtered by.")
  @GetMapping("/filter")
  public List<DoctorResponse> findAllFilteredBy(
      @RequestParam(required = false) String search,
      @RequestParam(required = false) DoctorSpecializationEnum specialization) {
    return DoctorMapper.toResponses(service.findAllFilteredBy(search, specialization));
  }

  @Operation(summary = "Get a doctor by id.")
  @GetMapping("/{id}")
  public DoctorResponse findById(@PathVariable Long id) {
    DoctorEntity entity = service.findById(id);
    return DoctorMapper.toResponse(entity);
  }

  @GetMapping("/specialization")
  @Operation(summary = "Get all doctor specializations.")
  public List<DoctorSpecializationResponse> findAllSpecializations() {
    return DoctorSpecializationMapper.toResponses(specializationService.findAll());
  }
}
