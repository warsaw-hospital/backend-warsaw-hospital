package com.warsaw.hospital.doctor;

import com.warsaw.hospital.doctor.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
  boolean existsByPersonalCode(String personalCode);
}
