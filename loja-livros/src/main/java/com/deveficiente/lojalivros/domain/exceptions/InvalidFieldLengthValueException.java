package com.deveficiente.lojalivros.domain.exceptions;

import static java.text.MessageFormat.format;

public class InvalidFieldLengthValueException extends DomainException {

  public InvalidFieldLengthValueException(String fieldName, int maxLenght) {
    super(format("O comprimento do campo {0} deve ser entre 1 e {1}.", fieldName, maxLenght));
  }
}
