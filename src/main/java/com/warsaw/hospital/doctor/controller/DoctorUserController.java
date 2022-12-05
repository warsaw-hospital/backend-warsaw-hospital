package com.warsaw.hospital.doctor.controller;

import com.warsaw.hospital.doctor.DoctorService;
import com.warsaw.hospital.doctor.entity.DoctorEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorUserController {
  private final DoctorService service;

  public DoctorUserController(DoctorService service) {
    this.service = service;
  }

  @Operation(summary = "Get all doctors filtered by.")
  @GetMapping
  public List<DoctorEntity> findAllFilteredBy() {
    return service.findAll();
  }
}
