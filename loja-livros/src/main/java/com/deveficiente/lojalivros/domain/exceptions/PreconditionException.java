package com.deveficiente.lojalivros.domain.exceptions;

import static java.text.MessageFormat.format;

public class PreconditionException extends RuntimeException {

  public PreconditionException() {
    super();
  }

  public PreconditionException(String message) {
    super(message);
  }

  public PreconditionException(String message, Object... arguments) {
    super(format(message, arguments));
  }
}