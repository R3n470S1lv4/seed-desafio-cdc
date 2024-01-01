package com.deveficiente.lojalivros.controller.compra.requests;

import com.deveficiente.lojalivros.controller.annotations.EntityExists;
import com.deveficiente.lojalivros.domain.ItemPedidoCompra;
import com.deveficiente.lojalivros.domain.Livro;
import com.deveficiente.lojalivros.domain.PedidoCompra;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemPedidoCompraRequest {

  @NotBlank
  @EntityExists(domainClass = Livro.class, fieldName = "livroId")
  private final String livroId;
  @Positive
  private final int quantidade;

  public ItemPedidoCompra of(PedidoCompra pedidoCompra) {
    return new ItemPedidoCompra(livroId, quantidade, pedidoCompra);
  }
}
