package com.warsaw.hospital.workschedule;

import com.warsaw.hospital.workschedule.entity.WorkScheduleTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface WorkScheduleTemplateRepository
    extends JpaRepository<WorkScheduleTemplateEntity, Long> {
  Boolean existsByDoctorIdAndDayOfWeek(Long doctorId, DayOfWeek dayOfWeek);

  List<WorkScheduleTemplateEntity> findAllByDoctorId(Long doctorId);
}
