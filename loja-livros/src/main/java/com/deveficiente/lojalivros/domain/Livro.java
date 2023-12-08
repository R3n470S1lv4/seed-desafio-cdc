package com.deveficiente.lojalivros.domain;

import static com.deveficiente.lojalivros.domain.ValidationUtils.isLengthGreaterThan;
import static com.deveficiente.lojalivros.domain.ValidationUtils.isLengthLessThan;
import static com.deveficiente.lojalivros.domain.ValidationUtils.isValueLessThan;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDateTime.now;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.deveficiente.lojalivros.domain.exceptions.InvalidFieldLengthValueException;
import com.deveficiente.lojalivros.domain.exceptions.MandatoryFieldException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
public class Livro {

  @Id
  @Column(name = "livro_id")
  private String id;
  private String isbn;
  private String titulo;
  private String resumo;
  private String sumario;
  private BigDecimal valor;
  @Column(name = "numero_paginas")
  private int numeroPaginas;
  @Column(name = "data_publicacao")
  private LocalDate dataPublicacao;
  @OneToOne
  @JoinColumn(name = "categoria_id")
  private Categoria categoria;
  @OneToOne
  @JoinColumn(name = "autor_id")
  private Autor autor;
  private LocalDateTime criadoEm;

  @Deprecated
  private Livro() {
  }

  @Builder
  public Livro(String isbn, String titulo, String resumo, String sumario, BigDecimal valor,
      int numeroPaginas,
      Categoria categoria, Autor autor) {

    validaParametros(isbn, titulo, resumo, sumario, valor, numeroPaginas, categoria, autor);

    this.id = UUID.randomUUID().toString();
    this.isbn = isbn;
    this.titulo = titulo;
    this.resumo = resumo;
    this.sumario = sumario;
    this.valor = valor;
    this.numeroPaginas = numeroPaginas;
    this.categoria = categoria;
    this.autor = autor;
    this.criadoEm = now();
  }

  public Livro(String id, String isbn, String titulo, String resumo, String sumario,
      BigDecimal valor, int numeroPaginas, Categoria categoria, Autor autor,
      LocalDate dataPublicacao) {

    this(isbn, titulo, resumo, sumario, valor, numeroPaginas, categoria, autor);

    if (dataPublicacao.isBefore(LocalDate.now()) || dataPublicacao.equals(LocalDate.now())) {
      throw new MandatoryFieldException("dataPublicacao", "E deve ser posterior a data atual.");
    }

    this.dataPublicacao = dataPublicacao;
    this.id = id;
  }

  public Livro updateDataPublicacao(LocalDate dataPublicacao) {
    return new Livro(this.id, this.isbn, this.titulo, this.resumo, this.sumario, this.valor,
        this.numeroPaginas, this.categoria, this.autor, dataPublicacao);
  }

  private static void validaParametros(String isbn, String titulo, String resumo, String sumario,
      BigDecimal valor,
      int numeroPaginas, Categoria categoria, Autor autor) {
    if (isBlank(isbn)) {
      throw new MandatoryFieldException("ISBN");
    }
    if (isBlank(titulo)) {
      throw new MandatoryFieldException("TITULO");
    }
    if (isLengthLessThan(resumo, 1) || isLengthGreaterThan(resumo, 500)) {
      throw new InvalidFieldLengthValueException("RESUMO", 500);
    }
    if (isBlank(sumario)) {
      throw new MandatoryFieldException("SUMARIO");
    }
    if (isValueLessThan(valor, valueOf(20))) {
      throw new MandatoryFieldException("VALOR", "E o valor minimo é 20.");
    }
    if (isValueLessThan(numeroPaginas, 100)) {
      throw new MandatoryFieldException("NUMERO DE PAGINAS",
          "E deve possuir no minimo 20 paginas.");
    }
    if (isNull(categoria)) {
      throw new MandatoryFieldException("CATEGORIA");
    }
    if (isNull(autor)) {
      throw new MandatoryFieldException("Autor");
    }
  }

}
