package com.deveficiente.lojalivros.domain.vo;

import static com.deveficiente.lojalivros.domain.Precondition.requireNonNull;

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
    this.endereco = requireNonNull(endereco, "O campo Email deve ser preenchido.")
        .nonBlank()
        .match(REGEX, "O campo Email tem um formato invalido.")
        .take();
  }

  @Override
  public String toString() {
    return endereco;
  }
}
