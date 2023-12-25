package com.deveficiente.lojalivros.controller.compra;

import com.deveficiente.lojalivros.domain.Pagamento;
import com.fasterxml.jackson.annotation.JsonAlias;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class NovaCompraRequest {

  @NotNull
  @Valid
  @JsonAlias("pessoa")
  private PessoaRequest pessoaRequest;

  public Pagamento of(EntityManager entityManager) {
    return new Pagamento(pessoaRequest.of(entityManager));
  }

  public String getPais() {
    return pessoaRequest.getEnderecoRequest().getPaisId();
  }

  public String getEstado() {
    return pessoaRequest.getEnderecoRequest().getEstadoId();
  }
}
