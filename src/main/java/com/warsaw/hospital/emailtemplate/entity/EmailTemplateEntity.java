package com.warsaw.hospital.emailtemplate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "email_template")
public class EmailTemplateEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String subject;
  private String body;

  public Long getId() {
    return id;
  }

  public EmailTemplateEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public EmailTemplateEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public EmailTemplateEntity setSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public String getBody() {
    return body;
  }

  public EmailTemplateEntity setBody(String body) {
    this.body = body;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EmailTemplateEntity)) return false;
    EmailTemplateEntity entity = (EmailTemplateEntity) o;
    return Objects.equals(getId(), entity.getId())
        && getName().equals(entity.getName())
        && getSubject().equals(entity.getSubject())
        && getBody().equals(entity.getBody());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getSubject(), getBody());
  }
}
