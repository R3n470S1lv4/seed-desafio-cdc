package com.deveficiente.lojalivros.controller.autor;

import com.deveficiente.lojalivros.domain.Autor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListaAutoresResponse {

  private String id;
  private final String nome;
  private final String email;
  private final String descricao;

  public static ListaAutoresResponse of(Autor autor) {
    return new ListaAutoresResponse(autor.getId(), autor.getNome(), autor.getEmail().getValue(),
        autor.getDescricao());
  }
}
