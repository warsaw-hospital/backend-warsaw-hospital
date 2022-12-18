package com.warsaw.hospital.auth.web.response;

import java.util.Objects;

public class StatusResponse {
  private Boolean isLoggedIn;
  private Boolean isDoctor;

  public Boolean getIsLoggedIn() {
    return isLoggedIn;
  }

  public StatusResponse setIsLoggedIn(Boolean isLoggedIn) {
    this.isLoggedIn = isLoggedIn;
    return this;
  }

  public Boolean getIsDoctor() {
    return isDoctor;
  }

  public StatusResponse setIsDoctor(Boolean isDoctor) {
    this.isDoctor = isDoctor;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof StatusResponse)) return false;
    StatusResponse that = (StatusResponse) o;
    return Objects.equals(getIsLoggedIn(), that.getIsLoggedIn())
        && Objects.equals(getIsDoctor(), that.getIsDoctor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getIsLoggedIn(), getIsDoctor());
  }
}
