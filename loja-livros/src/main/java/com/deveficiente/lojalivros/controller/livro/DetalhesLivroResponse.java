package com.deveficiente.lojalivros.controller.livro;

import com.deveficiente.lojalivros.domain.Livro;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DetalhesLivroResponse {

  private final String titulo;
  private final BigDecimal valor;
  private final String conteudo;
  private final String sumario;
  private final int numeroPaginas;
  private final String isbn;
  private final LocalDate dataPublicacao;
  private final AutorResponse autor;

  public static DetalhesLivroResponse of(Livro livro) {
    return DetalhesLivroResponse.builder()
        .titulo(livro.getTitulo())
        .valor(livro.getValor())
        .conteudo(livro.getResumo())
        .sumario(livro.getSumario())
        .numeroPaginas(livro.getNumeroPaginas())
        .isbn(livro.getIsbn())
        .dataPublicacao(livro.getDataPublicacao())
        .autor(
            new AutorResponse(livro.getAutor().getNome(), livro.getAutor().getDescricao())
        )
        .build();
  }

  @Getter
  @AllArgsConstructor
  private static class AutorResponse {

    private final String nome;
    private final String descricao;
  }
}
