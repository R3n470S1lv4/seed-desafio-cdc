package com.deveficiente.lojalivros.domain.exceptions;

import static java.text.MessageFormat.format;

public class InvalidFiledValueException extends PreConditionException {

  public InvalidFiledValueException(String fieldName, int minValue) {
    super(format("O valor do campo {0} deve ser no minimo de {1}.", fieldName, minValue));
  }
}
