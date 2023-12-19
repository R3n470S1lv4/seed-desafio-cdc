package com.deveficiente.lojalivros.domain.exceptions;

public static class PreconditionException extends RuntimeException {

    public PreconditionException(String message) {
      super(message);
    }

    public PreconditionException(String message, Object... arguments) {
      super(format(message, arguments));
    }
  }