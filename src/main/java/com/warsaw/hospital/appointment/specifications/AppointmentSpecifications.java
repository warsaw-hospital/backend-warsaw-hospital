package com.warsaw.hospital.appointment.specifications;

import com.warsaw.hospital.appointment.entity.AppointmentEntity;
import com.warsaw.hospital.appointment.entity.enums.AppointmentStatusEnum;
import io.micrometer.core.lang.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AppointmentSpecifications {
  public static Specification<AppointmentEntity> hasEqualDoctorId(Long doctorId) {
    return (root, query, cb) -> cb.equal(root.join("doctor").get("id"), doctorId);
  }

  public static Specification<AppointmentEntity> hasEqualPatientId(Long patientId) {
    return (root, query, cb) -> cb.equal(root.join("patient").get("id"), patientId);
  }

  public static Specification<AppointmentEntity> hasEqualStatus(AppointmentStatusEnum status) {
    return (root, query, cb) -> cb.equal(root.get("status"), status);
  }

  public static List<Specification<AppointmentEntity>> getSpecifications(
      @Nullable AppointmentStatusEnum status, @Nullable Long patientId, @Nullable Long doctorId) {
    List<Specification<AppointmentEntity>> specifications = new ArrayList<>();
    if (status == null && patientId == null && doctorId == null) {
      specifications.add(hasEqualDoctorId(doctorId));
    }
    if (status != null) {
      specifications.add(hasEqualPatientId(patientId));
    }
    if (status != null) {
      specifications.add(hasEqualStatus(status));
    }

    return specifications;
  }
}
