package com.warsaw.hospital.auth.web.response;

import java.util.Objects;

public class StatusResponse {
  private Boolean loggedIn;
  private Boolean admin;

  public Boolean getLoggedIn() {
    return loggedIn;
  }

  public StatusResponse setLoggedIn(Boolean loggedIn) {
    this.loggedIn = loggedIn;
    return this;
  }

  public Boolean getAdmin() {
    return admin;
  }

  public StatusResponse setAdmin(Boolean admin) {
    this.admin = admin;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof StatusResponse)) return false;
    StatusResponse that = (StatusResponse) o;
    return Objects.equals(getLoggedIn(), that.getLoggedIn())
        && Objects.equals(getAdmin(), that.getAdmin());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLoggedIn(), getAdmin());
  }
}
