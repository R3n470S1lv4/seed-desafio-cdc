package com.deveficiente.lojalivros.domain;

import static com.deveficiente.lojalivros.domain.Precondition.requireNonNull;
import static java.util.UUID.randomUUID;

import com.deveficiente.lojalivros.domain.vo.Pessoa;
import java.util.function.Function;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
public class Compra {

  @Id
  @Column(name = "compra_id")
  private String id;
  @Embedded
  private Pessoa pessoa;
  @OneToOne(mappedBy = "compra", cascade = CascadeType.ALL)
  private PedidoCompra pedidoCompra;

  /**
   * @deprecated Nao use esse construtor, ele so existe por causa do ORM
   */
  @Deprecated
  private Compra() {
  }

  @Builder
  public Compra(Pessoa pessoa, Function<Compra, PedidoCompra> pedidoCompra) {
    this.id = randomUUID().toString();
    this.pessoa = requireNonNull(pessoa).take();
    this.pedidoCompra = requireNonNull(pedidoCompra.apply(this)).take();
  }

}
