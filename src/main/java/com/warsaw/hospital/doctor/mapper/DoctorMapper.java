package com.warsaw.hospital.doctor.mapper;

import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.doctor.web.request.DoctorCreateRequest;
import com.warsaw.hospital.doctor.web.request.DoctorUpdateRequest;
import com.warsaw.hospital.doctor.web.response.DoctorResponse;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorMapper {
  public static DoctorResponse toResponse(DoctorEntity entity) {
    return new DoctorResponse();
  }

  public static List<DoctorResponse> toResponses(List<DoctorEntity> doctors) {
    return doctors.stream().map(DoctorMapper::toResponse).collect(Collectors.toList());
  }

  public static DoctorEntity toEntity(DoctorCreateRequest request) {
    return new DoctorEntity();
  }

  public static DoctorEntity toEntity(DoctorUpdateRequest request) {
    return new DoctorEntity();
  }
}
