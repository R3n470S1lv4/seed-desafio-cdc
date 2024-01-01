package com.deveficiente.lojalivros.controller.compra.requests;

import com.deveficiente.lojalivros.domain.vo.Pessoa;
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
  private DocumentoRequest documento;
  @NotNull
  @Valid
  private TelefoneRequest telefone;
  @NotNull
  @Valid
  private EnderecoRequest endereco;

  public Pessoa of(EntityManager entityManager) {
    return Pessoa.builder()
        .nome(nome)
        .sobreNome(sobreNome)
        .email(new com.deveficiente.lojalivros.domain.vo.Email(email))
        .documento(documento.of())
        .telefone(telefone.of())
        .endereco(endereco.of(entityManager))
        .build();
  }
}
