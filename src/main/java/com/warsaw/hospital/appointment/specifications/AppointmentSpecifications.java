package com.warsaw.hospital.appointment.specifications;

import com.warsaw.hospital.appointment.entity.AppointmentEntity;
import com.warsaw.hospital.appointment.entity.enums.AppointmentStatusEnum;
import io.micrometer.core.lang.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class AppointmentSpecifications {
  public static List<Specification<AppointmentEntity>> getSpecifications(
      @Nullable AppointmentStatusEnum status, @Nullable Long userId, @Nullable Long doctorId) {
    return List.of();
  }
}
