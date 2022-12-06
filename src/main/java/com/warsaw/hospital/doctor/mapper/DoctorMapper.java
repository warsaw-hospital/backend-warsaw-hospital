package com.warsaw.hospital.doctor.mapper;

import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.doctor.entity.DoctorSpecializationEntity;
import com.warsaw.hospital.doctor.entity.DoctorToSpecializationEntity;
import com.warsaw.hospital.doctor.web.request.DoctorCreateRequest;
import com.warsaw.hospital.doctor.web.request.DoctorUpdateRequest;
import com.warsaw.hospital.doctor.web.response.DoctorResponse;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorMapper {
  public static DoctorResponse toResponse(DoctorEntity entity) {
    return new DoctorResponse()
        .setId(entity.getId())
        .setName(entity.getName())
        .setLastname(entity.getLastname())
        .setEmail(entity.getEmail())
        .setPhoneNumber(entity.getPhoneNumber())
        .setDescription(entity.getDescription())
        .setSpecializations(
            entity.getSpecializations().stream()
                .map(DoctorToSpecializationEntity::getSpecialization)
                .map(DoctorSpecializationMapper::toResponse)
                .collect(Collectors.toList()));
  }

  public static List<DoctorResponse> toResponses(List<DoctorEntity> doctors) {
    return doctors.stream().map(DoctorMapper::toResponse).collect(Collectors.toList());
  }

  public static DoctorEntity toEntity(DoctorCreateRequest request) {
    return new DoctorEntity()
        .setName(request.getName())
        .setLastname(request.getLastname())
        .setEmail(request.getEmail())
        .setPassword(request.getPassword())
        .setPersonalCode(request.getPersonalCode())
        .setSpecializations(
            request.getSpecializationIds().stream()
                .map(new DoctorSpecializationEntity()::setId)
                .map(
                    specialization ->
                        new DoctorToSpecializationEntity().setSpecialization(specialization))
                .collect(Collectors.toList()))
        .setPhoneNumber(request.getPhoneNumber())
        .setDescription(request.getDescription());
  }

  public static DoctorEntity toEntity(DoctorUpdateRequest request) {
    return new DoctorEntity()
        .setId(request.getId())
        .setName(request.getName())
        .setLastname(request.getLastname())
        .setEmail(request.getEmail())
        .setPassword(request.getPassword())
        .setPersonalCode(request.getPersonalCode())
        .setSpecializations(
            request.getSpecializationIds().stream()
                .map(new DoctorSpecializationEntity()::setId)
                .map(
                    specialization ->
                        new DoctorToSpecializationEntity().setSpecialization(specialization))
                .collect(Collectors.toList()))
        .setPhoneNumber(request.getPhoneNumber())
        .setDescription(request.getDescription());
  }
}
