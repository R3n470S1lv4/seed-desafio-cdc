package com.deveficiente.lojalivros.controller.autor;

import com.deveficiente.lojalivros.domain.Autor;
import lombok.Getter;

@Getter
public class NovoAutorResponse {

  private final String id;
  private final String nome;
  private final String email;
  private final String descricao;

  public NovoAutorResponse(String id, String nome, String email, String descricao) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.descricao = descricao;
  }

  public static NovoAutorResponse of(Autor autor) {
    return new NovoAutorResponse(autor.getId(), autor.getNome(), autor.getEmail().getValue(),
        autor.getDescricao());
  }
}
