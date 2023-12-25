package com.deveficiente.lojalivros.domain;

import static com.deveficiente.lojalivros.domain.Precondition.requireNonNull;
import static java.math.BigInteger.valueOf;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

  /**
   * @deprecated Nao use esse construtor, ele so existe por causa do ORM
   */
  @Deprecated
  private Livro() {
  }

  @Builder
  public Livro(String isbn, String titulo, String resumo, String sumario, BigDecimal valor,
      int numeroPaginas, LocalDate dataPublicacao, Categoria categoria, Autor autor) {

    this.id = randomUUID().toString();
    this.isbn = requireNonNull(isbn, "O campo ISBN deve ser preenchido.")
        .nonBlank().take();
    this.titulo = requireNonNull(titulo, "O campo TITULO deve ser preenchido.")
        .nonBlank().take();
    this.resumo = requireNonNull(resumo, "O comprimento do campo RESUMO deve ser entre 1 e 500.")
        .LengthBetween(1, 500)
        .take();
    this.sumario = requireNonNull(sumario, "O campo SUMARIO deve ser preenchido.")
        .nonBlank()
        .take();
    this.valor = requireNonNull(valor, "O campo VALOR deve ser preenchido. E o valor minimo é 20.")
        .isNotLessThan(valueOf(20))
        .take();
    this.numeroPaginas = requireNonNull(numeroPaginas,
        "O campo NUMERO DE PAGINAS deve ser preenchido. E deve possuir no minimo 100 paginas.")
        .isNotLessThan(100).take();
    this.dataPublicacao = requireNonNull(dataPublicacao,
        "O campo DataPublicacao deve ser preenchido. E deve ser posterior a data atual.")
        .isAfter(LocalDate.now())
        .take();
    this.categoria = requireNonNull(categoria, "O campo CATEGORIA deve ser preenchido.").take();
    this.autor = requireNonNull(autor, "O campo AUTOR deve ser preenchido.").take();
    this.criadoEm = now();
  }
}
