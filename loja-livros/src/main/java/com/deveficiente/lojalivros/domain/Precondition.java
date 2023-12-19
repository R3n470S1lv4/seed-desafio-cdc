package com.deveficiente.lojalivros.domain;

import java.util.Collection;
import java.util.Objects;

public class Precondition<T> {

  private T value;
  private String message;

  private Precondition() {
  }

  public Precondition(T value, String message) {
    this.value = value;
    this.message = message;
  }

  public Precondition(T value) {
    this.value = value;
  }

  public static <T> Precondition<T> requireNonNull(T value, String message) {
    if (value == null) {
      throw new PreconditionException(message);
    }

    return new Precondition<>(value, message);
  }

  public static <T> Precondition<T> requireNonNull(T value) {
    if (value == null) {
      throw new PreconditionException();
    }

    return new Precondition<>(value);
  }

  public Precondition<T> andNonBlank() {
    if (isString() && ((String) this.value).isBlank()) {
      throw new PreconditionException(this.message);
    }
    return this;
  }

  private boolean isString() {
    return this.value instanceof String;
  }

  private boolean isInteger() {
    return this.value instanceof Integer;
  }

  private boolean isCollection() {
    return this.value instanceof Collection<?>;
  }

  private <R> R getValueAs(Class<R> returnType) {
    if (returnType.isInstance(this.value)) {
      return returnType.cast(this.value);
    } else {
      throw new PreconditionException("Invalid value type");
    }
  }

  public Precondition<T> andLengthGreaterThanOrEqualTo(int length, String message) {
    if (isString() && getValueAs(String.class).trim().length() < length) {
      throw new PreconditionException(message);
    }
    return this;
  }

  public T take() {
    return this.value;
  }

  public Precondition<T> andGreaterThan(int value, String message) {
    if (isInteger() && getValueAs(Integer.class) <= value) {
      throw new PreconditionException(message);
    }
    return this;
  }

  public Precondition<T> andHasNonNullElement(String message) {
    if (isCollection() && containsNullElement()) {
      throw new PreconditionException(message);
    }
    return this;
  }

  private boolean containsNullElement() {
    return ((Collection<?>) this.value).stream()
        .anyMatch(Objects::isNull);
  }

}
