package com.deveficiente.lojalivros.controller.exception.handler;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;

public record ApiErrorDTO(
    LocalDateTime timestamp,
    int status,
    String error,
    String message,
    List<FieldErrorDTO> errors
) {

  public ApiErrorDTO(HttpStatus status, String message) {
    this(now(), status.value(), status.getReasonPhrase(), message, List.of());
  }

  public ApiErrorDTO(HttpStatus status, List<FieldErrorDTO> errors) {
    this(now(), status.value(), status.getReasonPhrase(), "Validation failed", errors);
  }

}

