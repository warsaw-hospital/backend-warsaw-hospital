package com.warsaw.hospital.appointment.controller;

import com.warsaw.hospital.appointment.AppointmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/v1/appointment")
public class AppointmentAdminController {
    private final AppointmentService appointmentService;

    public AppointmentAdminController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
}
