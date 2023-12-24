package com.deveficiente.lojalivros.controller.compra;

import com.deveficiente.lojalivros.domain.vo.Pessoa;
import com.fasterxml.jackson.annotation.JsonAlias;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PessoaRequest {

  @NotBlank
  private String nome;
  @NotBlank
  private String sobreNome;
  @NotBlank
  @Email
  private String email;
  @NotNull
  @Valid
  @JsonAlias("documento")
  private DocumentoRequest documentoRequest;
  @NotNull
  @Valid
  @JsonAlias("telefone")
  private TelefoneRequest telefoneRequest;
  @NotNull
  @Valid
  @JsonAlias("endereco")
  private EnderecoRequest enderecoRequest;

  public Pessoa of(EntityManager entityManager) {
    return Pessoa.builder()
        .nome(nome)
        .sobreNome(sobreNome)
        .email(new com.deveficiente.lojalivros.domain.vo.Email(email))
        .documento(documentoRequest.of())
        .telefone(telefoneRequest.of())
        .endereco(enderecoRequest.of(entityManager))
        .build();
  }
}
