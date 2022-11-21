package com.warsaw.hospital.emailtemplate;

import com.warsaw.hospital.emailtemplate.entity.EmailTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplateEntity, Long> {
    Optional<EmailTemplateEntity> findByName(String name);
    Boolean existsByName(String name);
}
