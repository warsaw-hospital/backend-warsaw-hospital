package com.warsaw.hospital.appointment.controller;

import com.warsaw.hospital.appointment.AppointmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/appointment")
public class AppointmentUserController {
  private final AppointmentService appointmentService;

  public AppointmentUserController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }
}
