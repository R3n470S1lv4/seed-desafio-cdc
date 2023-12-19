package com.deveficiente.lojalivros.domain;

import static java.text.MessageFormat.format;

public class PreconditionException extends RuntimeException {

  public PreconditionException(String message) {
    super(message);
  }

  public PreconditionException() {
    super();
  }

  public PreconditionException(String message, Object... arguments) {
    super(format(message, arguments));
  }
}
