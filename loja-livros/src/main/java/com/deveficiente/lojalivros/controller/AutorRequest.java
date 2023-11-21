package com.deveficiente.lojalivros.controller;

import com.deveficiente.lojalivros.domain.Autor;
import com.deveficiente.lojalivros.domain.vo.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AutorRequest(@NotBlank String nome,
                           @NotBlank String email,
                           @NotBlank @Size(max = 400) String descricao) {

  public Autor to() {
    return new Autor(nome, new Email(email), descricao);
  }
}
