package com.warsaw.hospital.doctor;

import com.warsaw.hospital.doctor.entity.DoctorEntity;
import com.warsaw.hospital.doctor.enums.DoctorSpecializationEnum;
import com.warsaw.hospital.doctor.specifications.DoctorSpecifications;
import com.warsaw.hospital.exception.ApiException;
import com.warsaw.hospital.user.UserService;
import com.warsaw.hospital.user.entity.UserEntity;
import com.warsaw.hospital.user.entity.UserToDoctorEntity;
import com.warsaw.hospital.utils.SpecificationUtil;
import io.micrometer.core.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DoctorService {
  private final String ERROR_MESSAGE_BASE = "err.doctor.";
  private final DoctorRepository repository;
  private final UserService userService;

  public DoctorService(DoctorRepository repository, UserService userService) {
    this.repository = repository;
    this.userService = userService;
  }

  public List<DoctorEntity> findAll() {
    return repository.findAll();
  }

  public DoctorEntity findById(Long id) throws ApiException {
    return repository
        .findById(id)
        .orElseThrow(
            () -> ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", id));
  }

  public DoctorEntity findByPersonalCode(String personalCode) throws ApiException {
    return repository
        .findByPersonalCode(personalCode)
        .orElseThrow(
            () ->
                ApiException.notFound(ERROR_MESSAGE_BASE + "notFound")
                    .addLabel("personalCode", personalCode));
  }

  public Optional<DoctorEntity> maybeFindByUserId(Long userId) {
    return repository.findByUserId(userId);
  }

  public List<DoctorEntity> findAllFilteredBy(
      @Nullable String search, @Nullable DoctorSpecializationEnum specialization) {
    return repository.findAll(
        SpecificationUtil.toANDSpecification(
            DoctorSpecifications.getSpecifications(search, specialization)));
  }

  public DoctorEntity create(DoctorEntity entity) throws ApiException {

    if (repository.existsByPersonalCode(entity.getPersonalCode())) {
      throw ApiException.conflict(ERROR_MESSAGE_BASE + "personalCodeExists")
          .addLabel("personalCode", entity.getPersonalCode());
    }
    return repository.save(entity);
  }

  public DoctorEntity create(DoctorEntity entity, Long userId) throws ApiException {
    UserEntity userEntity = userService.findById(userId);
    entity.setUser(new UserToDoctorEntity(userEntity, entity));
    return create(entity);
  }

  public DoctorEntity update(DoctorEntity entity) throws ApiException {
    if (!repository.existsById(entity.getId())) {
      throw ApiException.conflict(ERROR_MESSAGE_BASE + "notFound").addLabel("id", entity.getId());
    }
    return repository.save(entity);
  }

  public void delete(Long id) throws ApiException {
    if (!repository.existsById(id)) {
      throw ApiException.conflict(ERROR_MESSAGE_BASE + "notFound").addLabel("id", id);
    }
    repository.deleteById(id);
  }
}
