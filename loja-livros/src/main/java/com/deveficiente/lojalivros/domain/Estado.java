package com.deveficiente.lojalivros.domain;

import static com.deveficiente.lojalivros.domain.Precondition.requireNonNull;
import static java.util.UUID.randomUUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table
@Getter
public class Estado {

  @Id
  @Column(name = "estado_id")
  private String id;
  private String nome;
  @ManyToOne
  @JoinColumn(name = "pais_id")
  private Pais pais;

  /**
   * @deprecated Nao use esse construtor, ele so existe por causa do ORM
   */
  @Deprecated
  private Estado() {
  }

  public Estado(String nome, Pais pais) {
    this.id = randomUUID().toString();
    this.nome = requireNonNull(nome, "O campo NOME deve ser preenchido.")
        .nonBlank()
        .take();
    this.pais = requireNonNull(pais, "O campo PAIS deve ser preenchido.").take();
  }

  public String getNomePais() {
    return pais.getNome();
  }

  public boolean isOf(Pais pais) {
    return pais.equals(this.pais);
  }

  public boolean isNotOf(Pais pais) {
    return !isOf(pais);
  }

}
