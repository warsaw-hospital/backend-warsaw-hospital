package com.warsaw.hospital.appointment;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AppointmentService {
    private final String ERROR_MESSAGE_BASE = "err.appointment.";
    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }
}
