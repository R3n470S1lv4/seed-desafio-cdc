package com.deveficiente.lojalivros.domain;


import static com.deveficiente.lojalivros.domain.Precondition.requireNonNull;
import static java.time.LocalDateTime.now;

import com.deveficiente.lojalivros.domain.vo.Email;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;


@Entity
@Getter
public class Autor {

  @Id
  @Column(name = "autor_id")
  private String id;
  private String nome;
  @Embedded
  private Email email;
  private String descricao;
  private LocalDateTime criadoEm;

  /**
   * @deprecated Nao use esse construtor, ele so existe por causa do ORM
   */
  @Deprecated
  private Autor() {
  }

  public Autor(String nome, Email email, String descricao) {
    this.id = UUID.randomUUID().toString();
    this.nome = requireNonNull(nome, "O campo Nome deve ser preenchido.")
        .nonBlank()
        .take();
    this.email = requireNonNull(email, "O campo Email deve ser preenchido.").take();
    this.descricao = requireNonNull(descricao, "O campo Descricao deve ser preenchido.")
        .nonBlank()
        .maxLength(400, "O campo Descricao nao pode passar de 400 caracteres.")
        .take();
    this.criadoEm = now();
  }
}
