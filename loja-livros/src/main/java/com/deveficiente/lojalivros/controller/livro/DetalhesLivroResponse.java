package com.deveficiente.lojalivros.controller.livro;

import com.deveficiente.lojalivros.domain.Autor;
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
  private final String resumo;
  private final String sumario;
  private final int numeroPaginas;
  private final String isbn;
  private final LocalDate dataPublicacao;
  private final AutorResponse autor;

  public static DetalhesLivroResponse of(Livro livro) {
    return DetalhesLivroResponse.builder()
        .titulo(livro.getTitulo())
        .valor(livro.getValor())
        .resumo(livro.getResumo())
        .sumario(livro.getSumario())
        .numeroPaginas(livro.getNumeroPaginas())
        .isbn(livro.getIsbn())
        .dataPublicacao(livro.getDataPublicacao())
        .autor(AutorResponse.of(livro.getAutor()))
        .build();
  }

  @Getter
  @AllArgsConstructor
  private static class AutorResponse {

    private final String nome;
    private final String descricao;

    public static AutorResponse of(Autor autor) {
      return new AutorResponse(autor.getNome(), autor.getDescricao());
    }
  }
}
