package com.deveficiente.lojalivros.domain;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.Validator;
import lombok.Getter;

@Getter
public enum TipoDocumento {
  CPF(new CPFValidator()),
  CNPJ(new CNPJValidator());

  private final Validator<String> validator;

  TipoDocumento(Validator<String> validator) {
    this.validator = validator;
  }

  public boolean isValid(String value) {
    try {
      String unformattedValue = value.replaceAll("[^0-9]", "");
      this.validator.assertValid(unformattedValue);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
