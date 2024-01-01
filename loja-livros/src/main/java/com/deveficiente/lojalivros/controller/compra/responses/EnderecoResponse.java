package com.deveficiente.lojalivros.controller.compra.responses;

import com.deveficiente.lojalivros.domain.vo.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EnderecoResponse {

  private String logradouro;
  private String bairro;
  private String cidade;
  private String estadoId;
  private String paisId;
  private String complemento;
  private String cep;

  public static EnderecoResponse of(Endereco endereco) {
    EnderecoResponseBuilder builder = EnderecoResponse.builder()
        .logradouro(endereco.getLogradouro())
        .bairro(endereco.getBairro())
        .cidade(endereco.getCidade())
        .paisId(endereco.getPaisId())
        .complemento(endereco.getComplemento())
        .cep(endereco.getCep());

    if (endereco.hasEstado()) {
      builder.estadoId(endereco.getEstadoId());
    }

    return builder.build();
  }
}
