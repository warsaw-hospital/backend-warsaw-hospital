package com.warsaw.hospital.user.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "account")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String lastname;

  private String email;

  private String password;

  @Column(unique = true)
  private String personalCode;

  private String phoneNumber;
  private String address;

  private LocalDateTime createdAt;
  private LocalDateTime lastLogin;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
  private List<UserToUserRoleEntity> roles = new ArrayList<>();

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
  private UserToDoctorEntity doctor;

  public Long getId() {
    return id;
  }

  public UserEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public UserEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getLastname() {
    return lastname;
  }

  public UserEntity setLastname(String surname) {
    this.lastname = surname;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserEntity setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getPersonalCode() {
    return personalCode;
  }

  public UserEntity setPersonalCode(String personalCode) {
    this.personalCode = personalCode;
    return this;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public UserEntity setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public UserEntity setAddress(String address) {
    this.address = address;
    return this;
  }

  public LocalDateTime getLastLogin() {
    return lastLogin;
  }

  public UserEntity setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
    return this;
  }

  public List<UserToUserRoleEntity> getRoles() {
    return roles;
  }

  public UserEntity setRoles(List<UserToUserRoleEntity> userRoles) {
    this.roles = userRoles;
    return this;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public UserEntity setCreatedAt(LocalDateTime creation) {
    this.createdAt = creation;
    return this;
  }

  public boolean addToUserRole(UserToUserRoleEntity toUserRole) {
    boolean doesNotExist =
        roles.stream()
            .map(UserToUserRoleEntity::getUserRole)
            .noneMatch(role -> role.getId().equals(toUserRole.getId()));
    if (doesNotExist) {
      if (toUserRole.getUser() == null) {
        toUserRole.setUser(this);
      }
      roles.add(toUserRole);
    }
    return doesNotExist;
  }

  public void removeToUserRole(UserToUserRoleEntity toUserRole) {
    if (roles.remove(toUserRole)) {
      toUserRole.setUser(null);
    }
  }

  public UserToDoctorEntity getDoctor() {
    return doctor;
  }

  public UserEntity setDoctor(UserToDoctorEntity doctor) {
    this.doctor = doctor;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserEntity that = (UserEntity) o;
    return Objects.equals(getId(), that.getId())
        && Objects.equals(getName(), that.getName())
        && Objects.equals(getLastname(), that.getLastname())
        && Objects.equals(getEmail(), that.getEmail())
        && Objects.equals(getPassword(), that.getPassword())
        && Objects.equals(getPersonalCode(), that.getPersonalCode())
        && Objects.equals(getPhoneNumber(), that.getPhoneNumber())
        && Objects.equals(getAddress(), that.getAddress())
        && Objects.equals(getCreatedAt(), that.getCreatedAt())
        && Objects.equals(getLastLogin(), that.getLastLogin())
        && Objects.equals(getRoles(), that.getRoles())
        && Objects.equals(getDoctor(), that.getDoctor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(),
        getName(),
        getLastname(),
        getEmail(),
        getPassword(),
        getPersonalCode(),
        getPhoneNumber(),
        getAddress(),
        getCreatedAt(),
        getLastLogin(),
        getDoctor());
  }
}
