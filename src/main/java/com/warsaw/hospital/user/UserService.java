package com.warsaw.hospital.user;

import com.warsaw.hospital.auth.config.AuthenticatedProfile;
import com.warsaw.hospital.exception.ApiException;
import com.warsaw.hospital.user.entity.UserEntity;
import com.warsaw.hospital.user.specifications.UserSpecifications;
import com.warsaw.hospital.user.web.request.UserInitialUpdateRequest;
import com.warsaw.hospital.user.web.request.UserUpdateRequest;
import com.warsaw.hospital.utils.SpecificationUtil;
import io.micrometer.core.lang.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {
  private final String ERROR_MESSAGE_BASE = "err.user.";
  private final UserRepository repository;
  private final PasswordEncoder encoder;

  public UserService(UserRepository repository, PasswordEncoder encoder) {
    this.repository = repository;
    this.encoder = encoder;
  }

  public List<UserEntity> findAll(@Nullable String search) {
    List<Specification<UserEntity>> specifications = new ArrayList<>();
    if (search != null) {
      specifications.add(UserSpecifications.filterBySearch(search));
    }
    specifications.add(UserSpecifications.hasNotNullCreationDate());
    return repository.findAll(SpecificationUtil.toANDSpecification(specifications));
  }

  public List<UserEntity> findAll() {
    return repository.findAll();
  }

  public UserEntity findById(Long id) throws ApiException {
    return repository
        .findById(id)
        .orElseThrow(
            () -> ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", id));
  }

  public UserEntity findByPersonalCode(String personalCode) throws ApiException {
    return repository
        .findByPersonalCode(personalCode)
        .orElseThrow(
            () ->
                ApiException.notFound(ERROR_MESSAGE_BASE + "notFound")
                    .addLabel("personalCode", personalCode));
  }

  public UserEntity findByPassChangeToken(String passChangeToken) throws ApiException {
    return repository
        .findByPassChangeToken(passChangeToken)
        .orElseThrow(
            () ->
                ApiException.notFound(ERROR_MESSAGE_BASE + "notFound")
                    .addLabel("passChangeToken", passChangeToken));
  }

  public UserEntity findByEmail(String email) throws ApiException {
    return repository
        .findByEmail(email)
        .orElseThrow(
            () -> ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("email", email));
  }

  public Optional<UserEntity> maybeFindByEmail(String email) {
    return repository.findByEmail(email);
  }

  public Optional<UserEntity> maybeFinfByPersonalCode(String personalCode) {
    return repository.findByPersonalCode(personalCode);
  }

  @Nullable
  public UserEntity getUser(AuthenticatedProfile profile) throws ApiException {
    if (profile == null) {
      return null;
    }
    return findById(profile.getId());
  }

  public UserEntity create(UserEntity entity) throws ApiException {
    if (repository.existsByPersonalCode(entity.getPersonalCode())) {
      throw ApiException.conflict(ERROR_MESSAGE_BASE + "duplicate")
          .addLabel("personalCode", entity.getPersonalCode());
    }
    return repository.save(entity);
  }

  public UserEntity update(UserEntity entity) throws ApiException {
    if (!repository.existsById(entity.getId())) {
      throw ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", entity.getId());
    }
    return repository.save(entity);
  }

  public Boolean existsByPersonalCode(String personalCode) {
    return repository.existsByPersonalCode(personalCode);
  }

  public Boolean existsByPasswordChangeToken(String passChangeToken) {
    return repository.existsByPassChangeToken(passChangeToken);
  }

  public boolean changePassword(
      String newPassword, String oldPassword, AuthenticatedProfile profile) throws ApiException {
    UserEntity user = getUser(profile);
    if (user == null) {
      throw ApiException.bad("err.user.mustBeAuthenticated");
    } else if (!encoder.matches(oldPassword, user.getPassword())) {
      return false;
    } else {
      user.setPassword(encoder.encode(newPassword));
      repository.save(user);
      return true;
    }
  }

  public UserEntity updateBy(UserUpdateRequest request, AuthenticatedProfile profile)
      throws ApiException {
    UserEntity user = findById(profile.getId());
    user.setPhoneNumber(request.getPhoneNumber()).setAddress(request.getAddress());
    return repository.save(user);
  }

  public UserEntity updateBy(UserInitialUpdateRequest request, AuthenticatedProfile profile) {

    UserEntity user = findById(profile.getId());

    user.setEmail(request.getEmail())
        .setPhoneNumber(request.getPhoneNumber())
        .setAddress(request.getAddress())
        .setPassword(encoder.encode(request.getPassword()));

    return repository.save(user);
  }

  public boolean existsById(Long userId) {
    return repository.existsById(userId);
  }

  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", id);
    }
    repository.deleteById(id);
  }
}
