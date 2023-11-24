package com.deveficiente.lojalivros.domain;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Table(name = "Categoria")
@Entity
@Getter
public class Categoria {

  @Id
  private String id;
  private String nome;

  @Deprecated
  private Categoria() {
  }

  public Categoria(String nome) {
    if (StringUtils.isBlank(nome)) {
      throw new DomainException("O campo Nome deve ser preenchido.");
    }
    this.id = UUID.randomUUID().toString();
    this.nome = nome;
  }
}
