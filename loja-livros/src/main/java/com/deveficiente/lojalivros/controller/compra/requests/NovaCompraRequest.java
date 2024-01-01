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

  public String getPais() {
    return pessoa.getEndereco().getPaisId();
  }

  public String getEstado() {
    return pessoa.getEndereco().getEstadoId();
  }

  public boolean hasNotEstado() {
    return isNull(getEstado());
  }
}
