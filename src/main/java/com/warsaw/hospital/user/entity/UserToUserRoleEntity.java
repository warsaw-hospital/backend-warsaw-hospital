package com.warsaw.hospital.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warsaw.hospital.userrole.entity.UserRoleEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "user_to_user_role")
public class UserToUserRoleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @JsonIgnore()
  private UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_role_id", referencedColumnName = "id")
  @JsonIgnore()
  private UserRoleEntity userRole;

  public Long getId() {
    return id;
  }

  public UserToUserRoleEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public UserEntity getUser() {
    return user;
  }

  public UserToUserRoleEntity setUser(UserEntity user) {
    this.user = user;
    return this;
  }

  public UserRoleEntity getUserRole() {
    return userRole;
  }

  public UserToUserRoleEntity setUserRole(UserRoleEntity userRole) {
    this.userRole = userRole;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserToUserRoleEntity)) return false;
    UserToUserRoleEntity that = (UserToUserRoleEntity) o;
    return getId().equals(that.getId())
        && Objects.equals(getUser(), that.getUser())
        && Objects.equals(getUserRole(), that.getUserRole());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUser(), getUserRole());
  }
}
