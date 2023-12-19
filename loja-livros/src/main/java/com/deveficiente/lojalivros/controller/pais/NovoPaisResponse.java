package com.deveficiente.lojalivros.controller.pais;


import com.deveficiente.lojalivros.domain.Pais;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NovoPaisResponse {

  private final String id;
  private final String nome;

  public static NovoPaisResponse of(Pais pais) {
    return new NovoPaisResponse(pais.getId(), pais.getNome());
  }
}
