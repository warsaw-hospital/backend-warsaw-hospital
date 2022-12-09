package com.warsaw.hospital.doctor;

import com.warsaw.hospital.doctor.entity.DoctorSpecializationEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class DoctorSpecializationService {
  private final String ERROR_MESSAGE_BASE = "err.specialization.";
  private final DoctorSpecializationRepository repository;

  public DoctorSpecializationService(DoctorSpecializationRepository repository) {
    this.repository = repository;
  }

  public List<DoctorSpecializationEntity> findAll() {
    return repository.findAll();
  }
}
