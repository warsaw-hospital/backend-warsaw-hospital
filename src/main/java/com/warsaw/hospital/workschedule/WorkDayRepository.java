package com.warsaw.hospital.workschedule;

import com.warsaw.hospital.workschedule.entity.WorkDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkDayRepository extends JpaRepository<WorkDayEntity, Long> {
  List<WorkDayEntity> findAllByWorkDateBetween(LocalDate fromDate, LocalDate toDate);

  Boolean existsByWorkDateAndDoctorId(LocalDate workDate, Long doctorId);

  List<WorkDayEntity> findAllByDoctorId(Long doctorId);
}
