package com.warsaw.hospital.email.entity;

import com.warsaw.hospital.email.entity.enums.EmailStatusEnum;
import com.warsaw.hospital.user.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "email")
public class EmailEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String subject;
  private String body;
  private LocalDateTime dateSent;

  @Enumerated(EnumType.STRING)
  private EmailStatusEnum status;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;

  public Long getId() {
    return id;
  }

  public EmailEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public EmailEntity setSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public String getBody() {
    return body;
  }

  public EmailEntity setBody(String body) {
    this.body = body;
    return this;
  }

  public LocalDateTime getDateSent() {
    return dateSent;
  }

  public EmailEntity setDateSent(LocalDateTime dateSent) {
    this.dateSent = dateSent;
    return this;
  }

  public EmailStatusEnum getStatus() {
    return status;
  }

  public EmailEntity setStatus(EmailStatusEnum status) {
    this.status = status;
    return this;
  }

  public UserEntity getUser() {
    return user;
  }

  public EmailEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EmailEntity)) return false;
    EmailEntity that = (EmailEntity) o;
    return getId().equals(that.getId())
        && getSubject().equals(that.getSubject())
        && Objects.equals(getBody(), that.getBody())
        && getDateSent().equals(that.getDateSent())
        && getStatus() == that.getStatus();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getSubject(), getBody(), getDateSent(), getStatus());
  }
}
