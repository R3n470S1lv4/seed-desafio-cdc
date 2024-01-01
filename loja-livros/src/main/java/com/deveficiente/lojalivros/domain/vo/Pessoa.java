package com.deveficiente.lojalivros.domain.vo;

import static com.deveficiente.lojalivros.domain.Precondition.requireNonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
public class Pessoa {

  private String nome;
  @Column(name = "sobrenome")
  private String sobreNome;
  private Email email;
  private Documento documento;
  private Telefone telefone;
  private Endereco endereco;

  /**
   * @deprecated Nao use esse construtor, ele so existe por causa do ORM
   */
  private Pessoa() {
  }

  @Builder
  public Pessoa(String nome, String sobreNome, Email email, Documento documento, Telefone telefone,
      Endereco endereco) {
    this.nome = requireNonNull(nome).nonBlank().take();
    this.sobreNome = requireNonNull(sobreNome).nonBlank().take();
    this.email = requireNonNull(email).take();
    this.documento = requireNonNull(documento).take();
    this.telefone = requireNonNull(telefone).take();
    this.endereco = requireNonNull(endereco).take();
  }

}
