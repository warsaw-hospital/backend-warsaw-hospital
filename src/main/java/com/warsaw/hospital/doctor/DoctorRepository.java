package com.warsaw.hospital.doctor;

import com.warsaw.hospital.doctor.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
}

