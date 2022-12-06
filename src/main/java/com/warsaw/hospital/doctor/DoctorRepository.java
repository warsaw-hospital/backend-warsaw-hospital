package com.warsaw.hospital.doctor;

import com.warsaw.hospital.doctor.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository
    extends JpaRepository<DoctorEntity, Long>, JpaSpecificationExecutor<DoctorEntity> {
  boolean existsByPersonalCode(String personalCode);

  Optional<DoctorEntity> findByPersonalCode(String personalCode);
}
