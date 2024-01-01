package com.deveficiente.lojalivros.controller.compra.requests;

import lombok.Getter;

@Getter
public class PessoaResponse {

  private String id;
  private String nome;
  private String sobreNome;
  private String email;
  private DocumentoRequest documento;
  private TelefoneRequest telefone;
  private EnderecoRequest endereco;

}
