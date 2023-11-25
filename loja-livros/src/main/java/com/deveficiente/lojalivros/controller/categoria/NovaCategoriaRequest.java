package com.deveficiente.lojalivros.controller.categoria;

import com.deveficiente.lojalivros.domain.Categoria;
import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NovaCategoriaRequest {

  @NotBlank
  private final String nome;

  @JsonCreator
  public NovaCategoriaRequest(String nome) {
    this.nome = nome;
  }

  public Categoria of() {
    return new Categoria(this.nome);
  }
}
