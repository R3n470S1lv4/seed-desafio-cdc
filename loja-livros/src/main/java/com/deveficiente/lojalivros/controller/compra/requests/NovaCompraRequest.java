package com.deveficiente.lojalivros.controller.compra.requests;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
  @Valid
  private CupomDescontoRequest cupom;

  public Compra of(EntityManager entityManager) {
    Compra compra = new Compra(pessoa.of(entityManager), pedido::of);
    adicionarCupom(entityManager, compra);
    return compra;
  }

  private void adicionarCupom(EntityManager entityManager, Compra compra) {
    if (nonNull(cupom)) {
      compra.adicionarCupom(cupom.of(entityManager));
    }
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
