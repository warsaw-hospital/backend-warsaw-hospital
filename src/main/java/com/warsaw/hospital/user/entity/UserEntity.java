package com.warsaw.hospital.user.entity;

import com.warsaw.hospital.user.entity.enums.UserType;

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
  private String surname;

  @Column(unique = true)
  private String email;

  private String password;

  @Column(unique = true)
  private String personalCode;

  private String phoneNumber;
  private String address;

  @Enumerated(EnumType.STRING)
  private UserType userType;

  private LocalDateTime lastLogin;
  private LocalDateTime creation;
  private Boolean fullyCreated;

  private Boolean verified;

  @Column(unique = true)
  private String passChangeToken;

  @Column(name = "pass_change_token_creation_date")
  private LocalDateTime tokenCreationDate;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
  private List<UserToUserRoleEntity> roles = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public UserEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public Boolean getFullyCreated() {
    return fullyCreated;
  }

  public UserEntity setFullyCreated(Boolean fullyCreated) {
    this.fullyCreated = fullyCreated;
    return this;
  }

  public String getName() {
    return name;
  }

  public UserEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getSurname() {
    return surname;
  }

  public UserEntity setSurname(String surname) {
    this.surname = surname;
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

  public UserType getUserType() {
    return userType;
  }

  public UserEntity setUserType(UserType userType) {
    this.userType = userType;
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

  public String getPassChangeToken() {
    return passChangeToken;
  }

  public UserEntity setPassChangeToken(String passChangeToken) {
    this.passChangeToken = passChangeToken;
    return this;
  }

  public Boolean getVerified() {
    return verified;
  }

  public UserEntity setVerified(Boolean verified) {
    this.verified = verified;
    return this;
  }

  public LocalDateTime getCreation() {
    return creation;
  }

  public UserEntity setCreation(LocalDateTime creation) {
    this.creation = creation;
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

  public LocalDateTime getTokenCreationDate() {
    return tokenCreationDate;
  }

  public UserEntity setTokenCreationDate(LocalDateTime tokenCreationDate) {
    this.tokenCreationDate = tokenCreationDate;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserEntity)) return false;
    UserEntity that = (UserEntity) o;
    return getId().equals(that.getId())
        && getName().equals(that.getName())
        && getSurname().equals(that.getSurname())
        && getEmail().equals(that.getEmail())
        && getPassword().equals(that.getPassword())
        && getPersonalCode().equals(that.getPersonalCode())
        && getPhoneNumber().equals(that.getPhoneNumber())
        && getAddress().equals(that.getAddress())
        && getCreation().equals(that.getCreation())
        && getFullyCreated().equals(that.getFullyCreated())
        && getUserType() == that.getUserType()
        && getTokenCreationDate() == that.getTokenCreationDate()
        && Objects.equals(getVerified(), that.getVerified())
        && Objects.equals(getLastLogin(), that.getLastLogin());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(),
        getName(),
        getSurname(),
        getEmail(),
        getFullyCreated(),
        getPassword(),
        getPersonalCode(),
        getPhoneNumber(),
        getAddress(),
        getCreation(),
        getUserType(),
        getTokenCreationDate(),
        getVerified(),
        getLastLogin());
  }
}
