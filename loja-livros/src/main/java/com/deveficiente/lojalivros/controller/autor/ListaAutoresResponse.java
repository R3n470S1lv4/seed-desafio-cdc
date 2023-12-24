package com.deveficiente.lojalivros.controller.autor;

import com.deveficiente.lojalivros.domain.Autor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListaAutoresResponse {

  private final String nome;
  private final String email;
  private final String descricao;
  private String id;

  public static ListaAutoresResponse of(Autor autor) {
    return new ListaAutoresResponse(autor.getId(), autor.getNome(), autor.getEmail().getEndereco(),
        autor.getDescricao());
  }
}
