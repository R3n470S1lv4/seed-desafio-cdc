package com.deveficiente.lojalivros.controller.compra.requests;

import static java.util.Objects.isNull;

import com.deveficiente.lojalivros.domain.Compra;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class NovaCompraRequest {

  @NotNull
  @Valid
  private PessoaRequest pessoa;
  @NotNull
  @Valid
  private NovoPedidoCompraRequest pedido;

  public Compra of(EntityManager entityManager) {
    return new Compra(pessoa.of(entityManager), pedido::of);
  }

  public String getPaisId() {
    return pessoa.getEndereco().getPaisId();
  }

  public String getEstadoId() {
    return pessoa.getEndereco().getEstadoId();
  }

  public boolean hasNotEstado() {
    return isNull(getEstadoId());
  }
}
