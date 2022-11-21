package com.warsaw.hospital.email.web.response;

import com.warsaw.hospital.email.entity.enums.EmailStatusEnum;

import java.time.LocalDateTime;
import java.util.Objects;

public class EmailResponse {
  private String subject;
  private String body;
  private LocalDateTime dateSent;
  private EmailStatusEnum status;

  public String getSubject() {
    return subject;
  }

  public EmailResponse setSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public String getBody() {
    return body;
  }

  public EmailResponse setBody(String body) {
    this.body = body;
    return this;
  }

  public LocalDateTime getDateSent() {
    return dateSent;
  }

  public EmailResponse setDateSent(LocalDateTime dateSent) {
    this.dateSent = dateSent;
    return this;
  }

  public EmailStatusEnum getStatus() {
    return status;
  }

  public EmailResponse setStatus(EmailStatusEnum status) {
    this.status = status;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EmailResponse)) return false;
    EmailResponse that = (EmailResponse) o;
    return Objects.equals(getSubject(), that.getSubject())
        && Objects.equals(getBody(), that.getBody())
        && Objects.equals(getDateSent(), that.getDateSent())
        && getStatus() == that.getStatus();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getSubject(), getBody(), getDateSent(), getStatus());
  }
}
