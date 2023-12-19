package com.deveficiente.lojalivros.controller.pais;

import com.deveficiente.lojalivros.controller.annotations.UniqueValue;
import com.deveficiente.lojalivros.domain.Pais;
import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NovoPaisRequest {


  @UniqueValue(domainClass = Pais.class, fieldName = "nome", message = "Ja existe um cadastro com o mesmo nome: {0}")
  @NotBlank
  private final String nome;

  @JsonCreator
  public NovoPaisRequest(String nome) {
    this.nome = nome;
  }

  public Pais of() {
    return new Pais(nome);
  }
}
