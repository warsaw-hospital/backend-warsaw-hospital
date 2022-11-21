package com.warsaw.hospital.email;

import com.warsaw.hospital.email.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
    Boolean existsBySubjectAndDateSent(String subject, LocalDateTime dateSent);
}
