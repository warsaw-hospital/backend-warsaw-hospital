package com.warsaw.hospital.appointment.mapper;

import com.warsaw.hospital.appointment.entity.AppointmentEntity;
import com.warsaw.hospital.appointment.web.request.AppointmentCreateRequest;
import com.warsaw.hospital.appointment.web.request.AppointmentUpdateRequest;
import com.warsaw.hospital.appointment.web.response.AppointmentResponse;
import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.doctor.mapper.DoctorMapper;
import com.warsaw.hospital.user.entity.UserEntity;
import com.warsaw.hospital.user.mapper.FullUserInfoMapper;

import java.util.List;
import java.util.stream.Collectors;

public class AppointmentMapper {
  public static AppointmentEntity toEntity(AppointmentCreateRequest request) {
    return new AppointmentEntity()
        .setDescription(request.getDescription())
        .setAppointmentDate(request.getAppointmentDate())
        .setAppointmentStartTime(request.getAppointmentStartTime())
        .setAppointmentEndTime(request.getAppointmentEndTime())
        .setStatus(request.getStatus())
        .setCreatedAt(request.getCreatedAt())
        .setDoctor(new DoctorEntity().setId(request.getDoctorId()))
        .setUser(new UserEntity().setId(request.getUserId()));
  }

  public static AppointmentEntity toEntity(AppointmentUpdateRequest request) {
    return new AppointmentEntity()
        .setId(request.getId())
        .setDescription(request.getDescription())
        .setAppointmentDate(request.getAppointmentDate())
        .setAppointmentStartTime(request.getAppointmentStartTime())
        .setAppointmentEndTime(request.getAppointmentEndTime())
        .setStatus(request.getStatus())
        .setCreatedAt(request.getCreatedAt())
        .setDoctor(new DoctorEntity().setId(request.getDoctorId()))
        .setUser(new UserEntity().setId(request.getUserId()));
  }

  public static AppointmentResponse toResponse(AppointmentEntity appointmentEntity) {
    return new AppointmentResponse()
        .setId(appointmentEntity.getId())
        .setDescription(appointmentEntity.getDescription())
        .setAppointmentDate(appointmentEntity.getAppointmentDate())
        .setAppointmentStartTime(appointmentEntity.getAppointmentStartTime())
        .setAppointmentEndTime(appointmentEntity.getAppointmentEndTime())
        .setStatus(appointmentEntity.getStatus())
        .setCreatedAt(appointmentEntity.getCreatedAt())
        .setDoctor(DoctorMapper.toResponse(appointmentEntity.getDoctor()))
        .setUser(FullUserInfoMapper.toInfoResponse(appointmentEntity.getUser()));
  }

  public static List<AppointmentResponse> toResponses(List<AppointmentEntity> appointmentEntities) {
    return appointmentEntities.stream()
        .map(AppointmentMapper::toResponse)
        .collect(Collectors.toList());
  }
}
