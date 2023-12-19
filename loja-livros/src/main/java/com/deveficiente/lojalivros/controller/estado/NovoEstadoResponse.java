package com.deveficiente.lojalivros.controller.estado;

import com.deveficiente.lojalivros.domain.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NovoEstadoResponse {

  private String id;
  private String nome;
  private String nomePais;

  public static NovoEstadoResponse of(Estado estado) {
    return new NovoEstadoResponse(estado.getId(), estado.getNome(), estado.getNomePais());
  }
}
