package com.deveficiente.lojalivros.domain;


import static java.time.LocalDateTime.now;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.deveficiente.lojalivros.domain.vo.Email;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "Autor")
@Entity
public class Autor {

  @Id
  private String id;
  private String nome;
  @Embedded
  private Email email;
  private String descricao;
  private LocalDateTime criadoEm;

  @Deprecated
  private Autor() {
  }

  public Autor(String nome, Email email, String descricao) {
    validaDadosEntrada(nome, email, descricao);

    this.id = UUID.randomUUID().toString();
    this.nome = nome;
    this.email = email;
    this.descricao = descricao;
    this.criadoEm = now();
  }

  private void validaDadosEntrada(String nome, Email email, String descricao) {
    if (isBlank(nome)) {
      throw new DomainException("O campo Nome deve ser preenchido.");
    }
    if (isBlank(descricao)) {
      throw new DomainException("O campo Descricao deve ser preenchido.");
    }
    if (descricao.length() > 400) {
      throw new DomainException("O campo Descricao nao pode passar de 400 caracteres.");
    }
    if (isNull(email)) {
      throw new DomainException("O campo Email deve ser preenchido.");
    }
  }
}
