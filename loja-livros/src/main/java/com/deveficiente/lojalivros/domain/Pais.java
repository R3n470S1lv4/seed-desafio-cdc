package com.deveficiente.lojalivros.domain;

import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity
@Table
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pais {

  @Id
  @Column(name = "PAIS_ID")
  private String id;
  @EqualsAndHashCode.Include
  private String nome;
  @OneToMany(mappedBy = "pais")
  private List<Estado> estados;


  /**
   * @deprecated Nao use esse construtor, ele so existe por causa do ORM
   */
  @Deprecated
  private Pais() {
  }

  public Pais(String nome) {
    this.id = randomUUID().toString();
    this.nome = requireNonNull(nome, "O campo NOME deve ser preenchido.");
  }

  public boolean isEstadoMandatory() {
    return !estados.isEmpty();
  }

}
