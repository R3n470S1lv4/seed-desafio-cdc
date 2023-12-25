package com.deveficiente.lojalivros.domain;

import static com.deveficiente.lojalivros.domain.ValidationUtils.isLengthGreaterThan;
import static com.deveficiente.lojalivros.domain.ValidationUtils.isLengthLessThan;

import com.deveficiente.lojalivros.domain.exceptions.PreconditionException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.regex.Pattern;

public class Precondition<T> {

  private T value;
  private String message;

  private Precondition() {
  }

  public Precondition(T value, String message) {
    this.value = value;
    this.message = message;
  }

  public static <T> Precondition<T> requireNonNull(T value, String message) {
    if (value == null) {
      throw new PreconditionException(message);
    }

    return new Precondition<>(value, message);
  }

  public static <T> Precondition<T> requireNonNull(T value) {
    if (value == null) {
      throw new PreconditionException("Value cannot be null");
    }

    return new Precondition<>(value, "Value cannot be null");
  }

  public Precondition<T> nonBlank() {
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

  private <R> R castTo(Class<R> returnType) {
    if (returnType.isInstance(this.value)) {
      return returnType.cast(this.value);
    } else {
      throw new PreconditionException("Invalid value type");
    }
  }

  public T take() {
    return this.value;
  }

  public Precondition<T> andGreaterThan(int value, String message) {
    if (isInteger() && castTo(Integer.class) <= value) {
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

  public Precondition<T> minLength(int minLength) {
    if (isString() && isLengthLessThan(castTo(String.class), minLength)) {
      throw new PreconditionException(message);
    }
    return this;
  }

  public Precondition<T> maxLength(int maxLength) {
    if (isString() && isLengthGreaterThan(castTo(String.class).trim(), maxLength)) {
      throw new PreconditionException(message);
    }
    return this;
  }

  public Precondition<T> maxLength(int maxLength, String message) {
    if (isString() && isLengthGreaterThan(castTo(String.class).trim(), maxLength)) {
      throw new PreconditionException(message);
    }
    return this;
  }

  public Precondition<T> LengthBetween(int minLength, int maxLength) {
    minLength(minLength);
    maxLength(maxLength);
    return this;
  }

  public Precondition<T> isNotLessThan(Number minValue) {
    if (ValidationUtils.isLessThan(castTo(Number.class), minValue)) {
      throw new PreconditionException(message);
    }
    return this;
  }

  public Precondition<T> isAfter(LocalDate now) {
    LocalDate other = castTo(LocalDate.class);
    if (now.isAfter(other) || now.isEqual(other)) {
      throw new PreconditionException(message);
    }
    return this;
  }

  public Precondition<T> match(String regex) {
    String value = castTo(String.class);

    if (!Pattern.compile(regex).matcher(value).matches()) {
      throw new PreconditionException(message);
    }
    return this;
  }

  public Precondition<T> match(String regex, String message) {
    this.message = message;
    return match(regex);
  }
}
