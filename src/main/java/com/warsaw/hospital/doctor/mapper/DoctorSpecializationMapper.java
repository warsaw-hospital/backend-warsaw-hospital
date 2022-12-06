package com.warsaw.hospital.doctor.mapper;

import com.warsaw.hospital.doctor.entity.DoctorSpecializationEntity;
import com.warsaw.hospital.doctor.web.response.DoctorSpecializationResponse;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorSpecializationMapper {
  public static DoctorSpecializationResponse toResponse(DoctorSpecializationEntity entity) {
    return new DoctorSpecializationResponse()
        .setId(entity.getId())
        .setName(entity.getName())
        .setDescription(entity.getDescription());
  }

  public static List<DoctorSpecializationResponse> toResponses(
      List<DoctorSpecializationEntity> entities) {
    return entities.stream()
        .map(DoctorSpecializationMapper::toResponse)
        .collect(Collectors.toList());
  }
}
