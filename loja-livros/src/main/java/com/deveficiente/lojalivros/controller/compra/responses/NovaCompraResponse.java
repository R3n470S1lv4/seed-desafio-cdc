package com.deveficiente.lojalivros.controller.compra.responses;

import com.deveficiente.lojalivros.domain.Compra;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NovaCompraResponse {

  private String id;
  private PessoaResponse pessoa;

  public static NovaCompraResponse of(Compra compra) {
    return NovaCompraResponse.builder()
        .id(compra.getId())
        .pessoa(PessoaResponse.of(compra.getPessoa()))
        .build();
  }
}
