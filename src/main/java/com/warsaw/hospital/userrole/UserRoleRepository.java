package com.warsaw.hospital.userrole;

import com.warsaw.hospital.userrole.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
  Optional<UserRoleEntity> findByName(String name);

  boolean existsByName(String name);
}
