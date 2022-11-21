package com.warsaw.hospital.userrole.web.response;

import java.util.Objects;

public class UserRoleResponse {
  private Long id;
  private String name;

  public Long getId() {
    return id;
  }

  public UserRoleResponse setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public UserRoleResponse setName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserRoleResponse)) return false;
    UserRoleResponse that = (UserRoleResponse) o;
    return getId().equals(that.getId()) && getName().equals(that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName());
  }
}
