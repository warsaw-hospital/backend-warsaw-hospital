package com.warsaw.hospital.emailtemplate.web.response;

import java.util.Objects;

public class EmailTemplateResponse {

  private Long id;
  private String name;
  private String body;
  private String subject;

  public Long getId() {
    return id;
  }

  public EmailTemplateResponse setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public EmailTemplateResponse setName(String name) {
    this.name = name;
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public EmailTemplateResponse setSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public String getBody() {
    return body;
  }

  public EmailTemplateResponse setBody(String body) {
    this.body = body;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EmailTemplateResponse)) return false;
    EmailTemplateResponse that = (EmailTemplateResponse) o;
    return Objects.equals(getId(), that.getId())
        && getName().equals(that.getName())
        && getSubject().equals(that.getSubject())
        && getBody().equals(that.getBody());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getSubject(), getBody());
  }
}
