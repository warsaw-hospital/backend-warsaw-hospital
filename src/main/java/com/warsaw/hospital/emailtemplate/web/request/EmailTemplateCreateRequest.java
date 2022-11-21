package com.warsaw.hospital.emailtemplate.web.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class EmailTemplateCreateRequest {
  @NotNull(message = "name is ${validatedValue}, but it cannot be null")
  @Size(
      min = 5,
      max = 500,
      message = "name is ${validatedValue}, but its length must be between {min} and {max}")
  private String name;

  @NotNull(message = "subject is ${validatedValue}, but it cannot be null")
  @Size(
      min = 5,
      max = 500,
      message = "subject is ${validatedValue}, but its length must be between {min} and {max}")
  private String subject;

  @NotNull(message = "body is ${validatedValue}, but it cannot be null")
  @Size(
      min = 5,
      max = 10000,
      message = "body is ${validatedValue}, but its length must be between {min} and {max}")
  private String body;

  public String getName() {
    return name;
  }

  public EmailTemplateCreateRequest setName(String name) {
    this.name = name;
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public EmailTemplateCreateRequest setSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public String getBody() {
    return body;
  }

  public EmailTemplateCreateRequest setBody(String body) {
    this.body = body;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EmailTemplateCreateRequest)) return false;
    EmailTemplateCreateRequest request = (EmailTemplateCreateRequest) o;
    return getName().equals(request.getName())
        && getSubject().equals(request.getSubject())
        && getBody().equals(request.getBody());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getSubject(), getBody());
  }
}
