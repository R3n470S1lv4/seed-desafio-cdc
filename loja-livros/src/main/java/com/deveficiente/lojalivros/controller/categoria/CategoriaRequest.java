package com.deveficiente.lojalivros.controller.categoria;

import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CategoriaRequest {

  @NotBlank
  private final String nome;

  @JsonCreator
  public CategoriaRequest(String nome) {
    this.nome = nome;
  }
}
