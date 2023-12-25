package com.deveficiente.lojalivros.domain;

import static com.deveficiente.lojalivros.domain.Precondition.requireNonNull;
import static java.util.UUID.randomUUID;

import com.deveficiente.lojalivros.domain.vo.Pessoa;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
//TODO MUDAR O NOME DA CLASS PARA COMPRA
public class Pagamento {

  @Id
  @Column(name = "pagamento_id")
  private String id;
  @Embedded
  private Pessoa pessoa;

  /**
   * @deprecated Nao use esse construtor, ele so existe por causa do ORM
   */
  @Deprecated
  private Pagamento() {
  }

  @Builder
  public Pagamento(Pessoa pessoa) {
    this.id = randomUUID().toString();
    this.pessoa = requireNonNull(pessoa).take();
  }
}
