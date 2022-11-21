package com.warsaw.hospital.userrole;

import com.warsaw.hospital.exception.ApiException;
import com.warsaw.hospital.userrole.entity.UserRoleEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserRoleService {
  private final String ERROR_MESSAGE_BASE = "err.userrole.";
  private final UserRoleRepository repository;

  public UserRoleService(UserRoleRepository repository) {
    this.repository = repository;
  }

  /**
   * This method return all email templates in the database.
   *
   * @return a list of user role entities.
   */
  public List<UserRoleEntity> findAll() {
    return repository.findAll();
  }

  /**
   * This method returns a user role by its id if it exists. If not, then an api exception will be
   * thrown.
   *
   * @param id user role id
   * @return existing user role.
   * @throws ApiException when user role does not exist.
   */
  public UserRoleEntity findById(Long id) throws ApiException {
    return repository
        .findById(id)
        .orElseThrow(
            () -> ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", id));
  }

  /**
   * "Find a user role by name, or throw an exception if it doesn't exist."
   *
   * <p>The first line of the function is the return statement. This is the value that will be
   * returned to the caller
   *
   * @param name The name of the method
   * @return A UserRoleEntity object
   * @throws ApiException when user role does not exist.
   */
  public UserRoleEntity findByName(String name) throws ApiException {
    return repository
        .findByName(name)
        .orElseThrow(
            () -> ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("name", name));
  }

  /**
   * This method saves given user role entity to database. If the user role already exist, then an
   * api exception will be thrown.
   *
   * @param entity a valid user role entity.
   * @return the saved user role entity (with id).
   * @throws ApiException when there is a duplicate user role.
   */
  public UserRoleEntity create(UserRoleEntity entity) throws ApiException {
    if (repository.existsByName(entity.getName())) {
      throw ApiException.conflict(ERROR_MESSAGE_BASE + "duplicate")
          .addLabel("name", entity.getName());
    }
    return repository.save(entity);
  }

  /**
   * This method updates existing user role. If it does not exist, then an api exception will be
   * thrown.
   *
   * @param entity a valid user role.
   * @return the saved user role.
   * @throws ApiException when user role does not exist.
   */
  public UserRoleEntity update(UserRoleEntity entity) throws ApiException {
    if (!repository.existsById(entity.getId())) {
      throw ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", entity.getId());
    }
    return repository.save(entity);
  }

  /**
   * This method deletes a user role by its id. If it does not exist, then an api exception will be
   * thrown.
   *
   * @param id user role id.
   * @throws ApiException when user role does not exist.
   */
  public void delete(Long id) throws ApiException {
    if (!repository.existsById(id)) {
      throw ApiException.notFound(ERROR_MESSAGE_BASE + "notFound").addLabel("id", id);
    }
    repository.deleteById(id);
  }
}
