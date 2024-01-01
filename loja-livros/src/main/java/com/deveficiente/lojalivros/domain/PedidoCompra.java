package com.deveficiente.lojalivros.domain;

import static java.util.UUID.randomUUID;

import java.util.List;
import java.util.function.Function;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;

@Getter
@Entity
public class PedidoCompra {

  @Id
  @Column(name = "pedido_compra_id")
  private String id;

  @OneToOne
  @JoinColumn(name = "compra_id")
  private Compra compra;

  @OneToMany(mappedBy = "pedidoCompra", cascade = CascadeType.ALL)
  private List<ItemPedidoCompra> items;

  private PedidoCompra() {
  }

  public PedidoCompra(Compra compra, Function<PedidoCompra, List<ItemPedidoCompra>> items) {
    this.id = randomUUID().toString();
    this.items = Precondition.requireNonNull(items.apply(this))
        .hasNonNullElement()
        .hasSizeAtLeast(1)
        .take();
    this.compra = compra;
  }

}
