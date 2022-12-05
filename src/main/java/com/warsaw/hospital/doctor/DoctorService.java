package com.warsaw.hospital.doctor;

import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.exception.ApiException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class DoctorService {
  private final String ERROR_MESSAGE_BASE = "err.user.";
  private final DoctorRepository repository;

  public DoctorService(DoctorRepository repository) {
    this.repository = repository;
  }

  public List<DoctorEntity> findAll() {
    return repository.findAll();
  }

  public DoctorEntity findById(Long id) throws ApiException {
    return repository
        .findById(id)
        .orElseThrow(
            () -> ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", id));
  }

  public DoctorEntity create(DoctorEntity entity) throws ApiException {

    if (repository.existsByPersonalCode(entity.getPersonalCode())) {
      throw ApiException.conflict(ERROR_MESSAGE_BASE + "personalCodeExists")
          .addLabel("personalCode", entity.getPersonalCode());
    }
    return repository.save(entity);
  }
}
