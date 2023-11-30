package com.deveficiente.lojalivros.domain.exceptions;

import static java.text.MessageFormat.format;

public class MandatoryFieldException extends DomainException {

  public MandatoryFieldException(String fieldName) {
    super(format("O campo {0} deve ser preenchido.", fieldName));
  }

  public MandatoryFieldException(String fieldName, String complementaryMessage) {
    super(format("O campo {0} deve ser preenchido. {1}", fieldName, complementaryMessage));
  }
}
