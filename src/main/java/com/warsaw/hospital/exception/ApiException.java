package com.warsaw.hospital.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ApiException extends RuntimeException {
  private final String message;
  private final HttpStatus status;
  private final Map<String, Object> labels = new HashMap<>();

  public ApiException(String message, HttpStatus status) {
    this.message = message;
    this.status = status;
  }

  public ApiException addLabel(String key, Object value) {
    labels.put(key, value);
    return this;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public static ApiException notFound(String message) {
    return new ApiException(message, HttpStatus.NOT_FOUND);
  }

  public static ApiException bad(String message) {
    return new ApiException(message, HttpStatus.BAD_REQUEST);
  }

  public static ApiException internalError(String message) {
    return new ApiException(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public static ApiException conflict(String message) {
    return new ApiException(message, HttpStatus.CONFLICT);
  }

  @Override
  public String getMessage() {
    if (labels.isEmpty()) {
      return this.message;
    }
    return this.message
        + " "
        + labels.keySet().stream()
            .map(key -> key + "=" + labels.get(key))
            .collect(Collectors.joining(", "));
  }
}
