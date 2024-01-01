package com.deveficiente.lojalivros.controller.compra.responses;

import com.deveficiente.lojalivros.domain.vo.Pessoa;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PessoaResponse {

  private String nome;
  private String sobreNome;
  private String email;
  @JsonAlias("documento")
  private DocumentoResponse documento;
  @JsonAlias("telefone")
  private TelefoneResponse telefone;
  @JsonAlias("endereco")
  private EnderecoResponse endereco;

  public static PessoaResponse of(Pessoa pessoa) {
    return PessoaResponse.builder()
        .nome(pessoa.getNome())
        .sobreNome(pessoa.getSobreNome())
        .email(pessoa.getEmail().getEndereco())
        .documento(DocumentoResponse.of(pessoa.getDocumento()))
        .telefone(TelefoneResponse.of(pessoa.getTelefone()))
        .endereco(EnderecoResponse.of(pessoa.getEndereco()))
        .build();
  }
}
