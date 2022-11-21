package com.warsaw.hospital.userrole.web.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UserRoleCreateRequest {
  @NotNull
  @Size(
      min = 2,
      max = 500,
      message = "name is ${validatedValue}, but its length must be between {min} and {max}")
  private String name;

  public String getName() {
    return name;
  }

  public UserRoleCreateRequest setName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserRoleCreateRequest)) return false;
    UserRoleCreateRequest that = (UserRoleCreateRequest) o;
    return getName().equals(that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
