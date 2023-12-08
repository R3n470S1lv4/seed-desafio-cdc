package com.deveficiente.lojalivros.domain;

import static java.time.LocalDateTime.now;

import com.deveficiente.lojalivros.domain.exceptions.PreConditionException;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Entity
@Getter
public class Categoria {

  @Id
  @Column(name = "categoria_id")
  private String id;
  private String nome;
  private LocalDateTime criadoEm;

  @Deprecated
  private Categoria() {
  }

  public Categoria(String nome) {
    if (StringUtils.isBlank(nome)) {
      throw new PreConditionException("O campo Nome deve ser preenchido.");
    }
    this.id = UUID.randomUUID().toString();
    this.nome = nome;
    this.criadoEm = now();
  }
}
