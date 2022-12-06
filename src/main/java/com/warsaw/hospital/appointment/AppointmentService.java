package com.warsaw.hospital.appointment;

import com.warsaw.hospital.appointment.entity.AppointmentEntity;
import com.warsaw.hospital.appointment.entity.enums.AppointmentStatusEnum;
import com.warsaw.hospital.appointment.specifications.AppointmentSpecifications;
import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.exception.ApiException;
import com.warsaw.hospital.user.UserService;
import com.warsaw.hospital.user.entity.UserEntity;
import com.warsaw.hospital.utils.SpecificationUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AppointmentService {
  private final String ERROR_MESSAGE_BASE = "err.appointment.";
  private final AppointmentRepository repository;
  private final UserService userService;

  public AppointmentService(AppointmentRepository repository, UserService userService) {
    this.repository = repository;
    this.userService = userService;
  }

  public List<AppointmentEntity> findAll() {
    return repository.findAll();
  }

  public AppointmentEntity findById(Long id) throws ApiException {
    return repository
        .findById(id)
        .orElseThrow(
            () -> ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", id));
  }

  public List<AppointmentEntity> findAllFilteredBy(
      AppointmentStatusEnum status, Long userId, Long doctorId) {
    return repository.findAll(
        SpecificationUtil.toANDSpecification(
            AppointmentSpecifications.getSpecifications(status, userId, doctorId)));
  }

  public AppointmentEntity create(AppointmentEntity entity) throws ApiException {
    if (repository.existsById(entity.getId())) {
      throw ApiException.conflict(ERROR_MESSAGE_BASE + "alreadyExists")
          .addLabel("id", entity.getId());
    }
    return repository.save(entity);
  }

  public AppointmentEntity userCreate(AppointmentEntity entity, Long userId) throws ApiException {
    UserEntity user = userService.findById(userId);
    entity.setUser(user);
    return create(entity);
  }

  public AppointmentEntity doctorCreate(AppointmentEntity entity, Long userId) throws ApiException {
    UserEntity user = userService.findById(userId);
    DoctorEntity doctor = user.getDoctor().getDoctor();
    entity.setDoctor(doctor);
    return create(entity);
  }

  public AppointmentEntity update(AppointmentEntity entity) throws ApiException {
    if (!repository.existsById(entity.getId())) {
      throw ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", entity.getId());
    }
    return repository.save(entity);
  }

  public AppointmentEntity userUpdate(AppointmentEntity entity, Long userId) throws ApiException {
    UserEntity user = userService.findById(userId);
    if (!user.getId().equals(entity.getUser().getId())) {
      throw ApiException.bad(ERROR_MESSAGE_BASE + "wrongUser");
    }
    entity.setUser(user);
    return update(entity);
  }

  public AppointmentEntity doctorUpdate(AppointmentEntity entity, Long userId) throws ApiException {
    UserEntity user = userService.findById(userId);
    DoctorEntity doctor = user.getDoctor().getDoctor();
    if (!doctor.getId().equals(entity.getDoctor().getId())) {
      throw ApiException.bad(ERROR_MESSAGE_BASE + "wrongDoctor");
    }
    entity.setDoctor(doctor);
    return update(entity);
  }

  public void delete(Long id) throws ApiException {
    if (!repository.existsById(id)) {
      throw ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", id);
    }
    repository.deleteById(id);
  }
}
