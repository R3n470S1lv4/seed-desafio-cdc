package com.deveficiente.lojalivros.controller;

import com.deveficiente.lojalivros.domain.Autor;
import com.deveficiente.lojalivros.domain.vo.Email;
import jakarta.validation.constraints.NotBlank;

public record AutorRequest(@NotBlank String nome,
                           String email,
                           String descricao) {

  public Autor to() {
    return new Autor(nome, new Email(email), descricao);
  }
}
