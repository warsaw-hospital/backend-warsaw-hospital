package com.warsaw.hospital.auth.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AuthenticatedProfile implements Authentication {
  private Long id;
  private Long representedUserId;
  private String accessToken;
  private String refreshToken;
  private boolean authenticated;
  private List<String> authorityList = new ArrayList<>();

  public AuthenticatedProfile() {}

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorityList.stream().map((SimpleGrantedAuthority::new)).collect(Collectors.toList());
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getDetails() {
    return this.id;
  }

  @Override
  public Object getPrincipal() {
    return this.id;
  }

  @Override
  public boolean isAuthenticated() {
    return this.authenticated;
  }

  @Override
  public String getName() {
    return null;
  }

  public Long getUserId() {
    if (representedUserId != null) {
      return representedUserId;
    }
    return id;
  }

  public Long getId() {
    return id;
  }

  public AuthenticatedProfile setId(Long userId) {
    this.id = userId;
    return this;
  }

  public Long getRepresentedUserId() {
    return representedUserId;
  }

  public AuthenticatedProfile setRepresentedUserId(Long representedUserId) {
    this.representedUserId = representedUserId;
    return this;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public AuthenticatedProfile setAccessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public AuthenticatedProfile setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
    return this;
  }

  public Boolean getAuthenticated() {
    return this.authenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    this.authenticated = isAuthenticated;
  }

  public AuthenticatedProfile setAuthorityList(List<String> authorityList) {
    this.authorityList = authorityList;
    return this;
  }
}
