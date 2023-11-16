package com.deveficiente.lojalivros.domain;


import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.deveficiente.lojalivros.domain.vo.Email;
import java.time.LocalDateTime;

public class Autor {

  private final String nome;
  private final Email email;
  private final String descricao;
  private final LocalDateTime criadoEm;

  public Autor(String nome, Email email, String descricao) {
    validaDadosEntrada(nome, email, descricao);

    this.nome = nome;
    this.email = email;
    this.descricao = descricao;
    this.criadoEm = LocalDateTime.now();
  }

  private static void validaDadosEntrada(String nome, Email email, String descricao) {
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
