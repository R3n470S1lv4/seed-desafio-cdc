package com.deveficiente.lojalivros.controller.categoria;

import com.deveficiente.lojalivros.domain.Categoria;
import lombok.Getter;

@Getter
public class NovaCategoriaResponse {


  private final String id;
  private final String nome;

  public NovaCategoriaResponse(String id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public static NovaCategoriaResponse of(Categoria categoria) {
    return new NovaCategoriaResponse(categoria.getId(), categoria.getNome());
  }
}
