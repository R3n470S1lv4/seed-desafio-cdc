package com.deveficiente.lojalivros.domain.vo;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.deveficiente.lojalivros.domain.exceptions.DomainException;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Email {

  private static final String REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
      + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

  @Column(name = "EMAIL")
  private final String value;

  @Deprecated
  private Email() {
    value = null;
  }

  public Email(String value) {
    if (isBlank(value)) {
      throw new DomainException("O campo Email deve ser preenchido.");
    }
    if (!Pattern.compile(REGEX).matcher(value).matches()) {
      throw new DomainException("O campo Email tem um formato invalido.");
    }

    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
