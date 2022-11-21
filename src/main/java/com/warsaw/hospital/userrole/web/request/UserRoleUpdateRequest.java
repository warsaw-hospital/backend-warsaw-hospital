package com.warsaw.hospital.userrole.web.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UserRoleUpdateRequest {
  @NotNull private Long id;

  @NotNull
  @Size(
      min = 2,
      max = 500,
      message = "name is ${validatedValue}, but its length must be between {min} and {max}")
  private String name;

  public Long getId() {
    return id;
  }

  public UserRoleUpdateRequest setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public UserRoleUpdateRequest setName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserRoleUpdateRequest)) return false;
    UserRoleUpdateRequest that = (UserRoleUpdateRequest) o;
    return getId().equals(that.getId()) && getName().equals(that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName());
  }
}
