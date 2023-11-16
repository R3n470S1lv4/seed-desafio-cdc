package com.deveficiente.lojalivros.domain.vo;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.deveficiente.lojalivros.domain.DomainException;
import java.util.regex.Pattern;

public class Email {

  private static final String REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
      + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

  private final String value;

  public Email(String value) {
    if (isBlank(value)) {
      throw new DomainException("O campo Email deve ser preenchido.");
    }
    if (!Pattern.compile(REGEX).matcher(value).matches()) {
      throw new DomainException("O campo Email tem um formato invalido.");
    }

    this.value = value;
  }
}
