package com.warsaw.hospital.doctor.specifications;

import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.doctor.enums.DoctorSpecializationEnum;
import io.micrometer.core.lang.Nullable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class DoctorSpecifications {
  //TODO: add more specifications
  public static List<Specification<DoctorEntity>> getSpecifications(
      @Nullable String search, @Nullable DoctorSpecializationEnum specialization) {
    return List.of();
  }
}
