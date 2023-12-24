package com.deveficiente.lojalivros.domain.exceptions;

import static java.text.MessageFormat.format;

public class PosconditionException extends RuntimeException {


  public PosconditionException() {
    super("Expected value is not valid.");
  }

  public PosconditionException(String message) {
    super(message);
  }

  public PosconditionException(String message, Object... arguments) {
    super(format(message, arguments));
  }

}