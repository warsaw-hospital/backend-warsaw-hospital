package com.warsaw.hospital.user;

import com.warsaw.hospital.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
    extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

  Optional<UserEntity> findByPersonalCode(String personalCode);

  Optional<UserEntity> findByEmail(String email);

  Boolean existsByPersonalCode(String personalCode);
}
