package com.deveficiente.lojalivros.domain.exceptions;

import static java.text.MessageFormat.format;

public class PreConditionException extends RuntimeException {

  public PreConditionException(String menssage) {
    super(menssage);
  }

  public PreConditionException(String menssage, Object... args) {
    super(format(menssage, args));
  }
}
