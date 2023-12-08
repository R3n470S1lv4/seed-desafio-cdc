package com.deveficiente.lojalivros.domain;


import static java.time.LocalDateTime.now;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.deveficiente.lojalivros.domain.exceptions.PreConditionException;
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

  @Deprecated
  private Autor() {
  }

  public Autor(String nome, Email email, String descricao) {
    validaParametros(nome, email, descricao);

    this.id = UUID.randomUUID().toString();
    this.nome = nome;
    this.email = email;
    this.descricao = descricao;
    this.criadoEm = now();
  }

  private void validaParametros(String nome, Email email, String descricao) {
    if (isBlank(nome)) {
      throw new PreConditionException("O campo Nome deve ser preenchido.");
    }
    if (isBlank(descricao)) {
      throw new PreConditionException("O campo Descricao deve ser preenchido.");
    }
    if (descricao.length() > 400) {
      throw new PreConditionException("O campo Descricao nao pode passar de 400 caracteres.");
    }
    if (isNull(email)) {
      throw new PreConditionException("O campo Email deve ser preenchido.");
    }
  }
}
