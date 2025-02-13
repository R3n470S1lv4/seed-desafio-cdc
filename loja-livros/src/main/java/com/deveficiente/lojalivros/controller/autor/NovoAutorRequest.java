package com.deveficiente.lojalivros.controller.autor;

import com.deveficiente.lojalivros.controller.annotations.UniqueValue;
import com.deveficiente.lojalivros.domain.Autor;
import com.deveficiente.lojalivros.domain.vo.Email;
import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class NovoAutorRequest {

  @NotBlank
  private final String nome;
  @NotBlank
  @UniqueValue(domainClass = Autor.class, fieldName = "email.endereco", message = "Já existe um autor com esse endereço de email: {0}.")
  private final String email;
  @NotBlank
  @Size(max = 400)
  private final String descricao;

  @JsonCreator
  public NovoAutorRequest(String nome, String email, String descricao) {
    this.nome = nome;
    this.email = email;
    this.descricao = descricao;
  }

  public Autor to() {
    return new Autor(nome, new Email(email), descricao);
  }
}
