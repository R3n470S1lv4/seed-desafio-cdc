package com.deveficiente.lojalivros.controller.livro;

import com.deveficiente.lojalivros.domain.Livro;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;

@Getter
public class NovoLivroResponse {

  private final String id;
  private final String isbn;
  private final String titulo;
  private final String resumo;
  private final String sumario;
  private final BigDecimal valor;
  private final Integer numeroPaginas;
  private final LocalDate dataPublicacao;
  private final String categoriaId;
  private final String autorId;

  public NovoLivroResponse(String id, String isbn, String titulo, String resumo, String sumario,
      BigDecimal valor, Integer numeroPaginas, LocalDate dataPublicacao, String categoriaId,
      String autorId) {
    this.id = id;
    this.isbn = isbn;
    this.titulo = titulo;
    this.resumo = resumo;
    this.sumario = sumario;
    this.valor = valor;
    this.numeroPaginas = numeroPaginas;
    this.dataPublicacao = dataPublicacao;
    this.categoriaId = categoriaId;
    this.autorId = autorId;
  }

  public static NovoLivroResponse of(Livro livro) {
    return new NovoLivroResponse(livro.getId(), livro.getIsbn(), livro.getTitulo(),
        livro.getResumo(), livro.getSumario(), livro.getValor(), livro.getNumeroPaginas(),
        livro.getDataPublicacao(), livro.getCategoria().getId(), livro.getAutor().getId());
  }
}
