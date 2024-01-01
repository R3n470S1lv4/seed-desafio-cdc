package com.deveficiente.lojalivros.controller.compra.requests;

import static com.deveficiente.lojalivros.domain.Precondition.requireNonNull;

import com.deveficiente.lojalivros.domain.Compra;
import com.deveficiente.lojalivros.domain.ItemPedidoCompra;
import com.deveficiente.lojalivros.domain.PedidoCompra;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NovoPedidoCompraRequest {

  @Positive
  @NotNull
  private final BigDecimal total;
  @Size(min = 1)
  @Valid
  private final List<ItemPedidoCompraRequest> itens;

  public PedidoCompra of(Compra compra) {
    validaQuantidadeItens();
    return new PedidoCompra(compra, this::itemsOf);
  }

  private void validaQuantidadeItens() {
    Integer totalItens = itens.stream()
        .map(ItemPedidoCompraRequest::getQuantidade)
        .reduce(0, Integer::sum);

    requireNonNull(totalItens)
        .isNotEquals(total, "A quantidade de itens calculado {0}, diverge do total informado {1}.");
  }

  private List<ItemPedidoCompra> itemsOf(PedidoCompra pedidoCompra) {
    return itens.stream()
        .map(item -> item.of(pedidoCompra))
        .toList();
  }
}
