package com.deveficiente.lojalivros.domain.vo;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.deveficiente.lojalivros.domain.exceptions.PreconditionException;
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
  private final String endereco;

  @Deprecated
  private Email() {
    endereco = null;
  }

  public Email(String endereco) {
    if (isBlank(endereco)) {
      throw new PreconditionException("O campo Email deve ser preenchido.");
    }
    if (!Pattern.compile(REGEX).matcher(endereco).matches()) {
      throw new PreconditionException("O campo Email tem um formato invalido.");
    }

    this.endereco = endereco;
  }

  @Override
  public String toString() {
    return endereco;
  }
}
