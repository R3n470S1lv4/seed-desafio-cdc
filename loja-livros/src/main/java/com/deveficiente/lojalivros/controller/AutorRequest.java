package com.deveficiente.lojalivros.controller;

import com.deveficiente.lojalivros.domain.Autor;
import com.deveficiente.lojalivros.domain.vo.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AutorRequest {

  @NotBlank
  private final String nome;
  @NotBlank
  private final String email;
  @NotBlank
  @Size(max = 400)
  private final String descricao;

  public AutorRequest(String nome, String email, String descricao) {
    this.nome = nome;
    this.email = email;
    this.descricao = descricao;
  }

  public Autor to() {
    return new Autor(nome, new Email(email), descricao);
  }
}
