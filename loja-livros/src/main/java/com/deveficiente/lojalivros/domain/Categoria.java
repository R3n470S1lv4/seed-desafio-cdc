package com.deveficiente.lojalivros.domain;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Categoria {

  @Id
  @Column(name = "categoria_id")
  private String id;
  private String nome;
  private LocalDateTime criadoEm;

  /**
   * @deprecated Nao use esse construtor, ele so existe por causa do ORM
   */
  @Deprecated
  private Categoria() {
  }

  public Categoria(String nome) {

    this.id = randomUUID().toString();
    this.nome = Precondition.requireNonNull(nome, "O campo Nome deve ser preenchido.")
        .nonBlank()
        .take();
    this.criadoEm = now();
  }
}
