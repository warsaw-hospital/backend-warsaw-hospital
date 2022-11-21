package com.warsaw.hospital.user.entity.enums;

import java.util.Arrays;

public enum UserType {
  PHYSICAL("PHYSICAL"), // This user is created by individual
  JURIDICAL("JURIDICAL"); // This user is created by corporate

  private final String type;

  UserType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public static UserType toEnum(String type) {
    return Arrays.stream(UserType.values())
        .filter((statusEnum) -> statusEnum.getType().equals(type))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("WRONG USER TYPE"));
  }
}
