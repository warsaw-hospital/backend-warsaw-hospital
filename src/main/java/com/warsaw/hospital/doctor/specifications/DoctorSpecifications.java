package com.warsaw.hospital.doctor.specifications;

import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.doctor.enums.DoctorSpecializationEnum;
import io.micrometer.core.lang.Nullable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoctorSpecifications {

  public static Specification<DoctorEntity> filterBySearch(String search) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();
      List<String> words = Arrays.stream(search.split(" ")).map(word -> "%" + word + "%").toList();
      for (String word : words) {
        predicates.add(cb.like(cb.lower(root.get("name")), word.toLowerCase()));
        predicates.add(cb.like(cb.lower(root.get("surname")), word.toLowerCase()));
        predicates.add(cb.like(cb.lower(root.get("email")), word.toLowerCase()));
        predicates.add(cb.like(root.get("phoneNumber"), word));
      }
      return cb.or(predicates.toArray(new Predicate[0]));
    };
  }

  public static Specification<DoctorEntity> hasEqualSpecialization(
      DoctorSpecializationEnum specialization) {
    return (root, query, cb) -> cb.equal(root.get("specialization"), specialization);
  }

  public static List<Specification<DoctorEntity>> getSpecifications(
      @Nullable String search, @Nullable DoctorSpecializationEnum specialization) {
    List<Specification<DoctorEntity>> specifications = new ArrayList<>();
    if (search != null) {
      specifications.add(filterBySearch(search));
    }
    if (specialization != null) {
      specifications.add(hasEqualSpecialization(specialization));
    }
    return specifications;
  }
}
