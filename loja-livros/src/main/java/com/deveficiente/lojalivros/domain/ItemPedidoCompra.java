package com.deveficiente.lojalivros.domain;

import static com.deveficiente.lojalivros.domain.Precondition.requireNonNull;
import static java.math.BigDecimal.ZERO;
import static java.util.UUID.randomUUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class ItemPedidoCompra {

  @Id
  @Column(name = "item_pedido_compra_id")
  private String id;

  @ManyToOne
  @JoinColumn(name = "pedido_compra_id")
  private PedidoCompra pedidoCompra;
  private String livroId;
  private Integer quantidade;

  private ItemPedidoCompra() {
  }

  public ItemPedidoCompra(String livroId, Integer quantidade, PedidoCompra pedidoCompra) {
    this.id = randomUUID().toString();
    this.livroId = requireNonNull(livroId).take();
    this.quantidade = requireNonNull(quantidade)
        .isNotLessThan(ZERO)
        .take();
    this.pedidoCompra = requireNonNull(pedidoCompra).take();
  }

}
