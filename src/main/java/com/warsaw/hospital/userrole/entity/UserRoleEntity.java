package com.warsaw.hospital.userrole.entity;

import com.warsaw.hospital.user.entity.UserToUserRoleEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "user_role")
public class UserRoleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(cascade = CascadeType.MERGE, mappedBy = "userRole", orphanRemoval = true)
  private List<UserToUserRoleEntity> users = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public UserRoleEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public UserRoleEntity setName(String name) {
    this.name = name;
    return this;
  }

  public List<UserToUserRoleEntity> getUsers() {
    return users;
  }

  public UserRoleEntity setUsers(List<UserToUserRoleEntity> users) {
    this.users = users;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserRoleEntity)) return false;
    UserRoleEntity that = (UserRoleEntity) o;
    return getId().equals(that.getId()) && getName().equals(that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName());
  }
}
