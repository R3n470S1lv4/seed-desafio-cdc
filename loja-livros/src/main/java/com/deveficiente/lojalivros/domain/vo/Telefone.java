package com.deveficiente.lojalivros.domain.vo;

import com.deveficiente.lojalivros.domain.Precondition;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Telefone {

  @Column(name = "NUMERO_TELEFONE")
  private String numero;

  /**
   * @deprecated Nao use esse construtor, ele so existe por causa do ORM
   */
  private Telefone() {
  }

  public Telefone(String numero) {
    this.numero = Precondition.requireNonNull(numero).take();
  }
}
