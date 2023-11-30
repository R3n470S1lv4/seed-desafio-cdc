package com.deveficiente.lojalivros.domain;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

import com.deveficiente.lojalivros.domain.exceptions.DomainException;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LivroTest {

  public static final String RESUMO_COM_499_CHARS = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce faucibus augue a risus commodo eleifend. Pellentesque tincidunt laoreet diam vitae luctus. Aenean nulla justo, vestibulum ut leo eu, varius finibus lectus. In ac velit lectus. Aliquam tempor tempor dolor, sed facilisis velit posuere eget. Vestibulum facilisis felis nulla, a egestas eros luctus sed. Sed accumsan, mi vel posuere suscipit, ipsum nisi volutpat purus, tempus vulputate tellus libero vitae tellus. Aliquam auctor lectus in.";
  public static final String RESUMO_COM_500_CHARS = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce faucibus augue a risus commodo eleifend. Pellentesque tincidunt laoreet diam vitae luctus. Aenean nulla justo, vestibulum ut leo eu, varius finibus lectus. In ac velit lectus. Aliquam tempor tempor dolor, sed facilisis velit posuere eget. Vestibulum facilisis felis nulla, a egestas eros luctus sed. Sed accumsan, mi vel posuere suscipit, ipsum nisi volutpat purus, tempus vulputate tellus libero vitae tellus. Aliquam auctor lectus in. x";

  @ParameterizedTest
  @NullAndEmptySource()
  void deveValidarISBNObrigatorio(String isbn) {
    assertThatThrownBy(() ->
        new Livro(null, "dummy", "dummy", new BigDecimal("23"), 100, mock(Categoria.class),
            mock(Autor.class))
    ).isInstanceOf(DomainException.class)
        .hasMessage("O campo ISBN deve ser preenchido.");
  }

  @ParameterizedTest
  @NullAndEmptySource()
  void deveValidarTituloObrigatorio(String titulo) {
    assertThatThrownBy(() ->
        new Livro("dummy", titulo, "dummy", new BigDecimal("23"), 100, mock(Categoria.class),
            mock(Autor.class))
    ).isInstanceOf(DomainException.class)
        .hasMessage("O campo TITULO deve ser preenchido.");
  }

  @ParameterizedTest
  @NullAndEmptySource()
  @ValueSource(strings = RESUMO_COM_499_CHARS)
  void deveValidarResumoObrigatorio(String resumo) {
    assertThatThrownBy(() ->
        new Livro("dummy", "dummy", resumo, new BigDecimal("23"), 100, mock(Categoria.class),
            mock(Autor.class))
    ).isInstanceOf(DomainException.class)
        .hasMessage("O comprimento do campo RESUMO deve ser entre 1 e 500.");
  }


  @ParameterizedTest
  @MethodSource("provideInvalidValuesForFieldValor")
  void deveValidarValorObrigatorio(BigDecimal valor) {
    assertThatThrownBy(() ->
        new Livro("dummy", "dummy", RESUMO_COM_500_CHARS, valor, 100, mock(Categoria.class),
            mock(Autor.class))
    ).isInstanceOf(DomainException.class)
        .hasMessage("O campo VALOR deve ser preenchido. E o valor minimo é 20.");
  }

  public static Stream<Arguments> provideInvalidValuesForFieldValor() {
    return Stream.of(Arguments.of(valueOf(19)));
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 0, 10, 20, 99})
  void deveValidarNumeroPaginasObrigatorio(int numeroPaginas) {
    assertThatThrownBy(() ->
        new Livro("dummy", "dummy", RESUMO_COM_500_CHARS, valueOf(20), numeroPaginas,
            mock(Categoria.class),
            mock(Autor.class))
    ).isInstanceOf(DomainException.class)
        .hasMessage(
            "O campo NUMERO DE PAGINAS deve ser preenchido. E deve possuir no minimo 20 paginas.");
  }

  @Test
  void deveValidarCategoriaObrigatoria() {
    assertThatThrownBy(() ->
        new Livro("dummy", "dummy", RESUMO_COM_500_CHARS, valueOf(20), 100,
            null,
            mock(Autor.class))
    ).isInstanceOf(DomainException.class)
        .hasMessage(
            "O campo CATEGORIA deve ser preenchido.");
  }

  @Test
  void deveValidarAutorObrigatoria() {
    assertThatThrownBy(() ->
        new Livro("dummy", "dummy", RESUMO_COM_500_CHARS, valueOf(20), 100,
            mock(Categoria.class),
            null)
    ).isInstanceOf(DomainException.class)
        .hasMessage(
            "O campo Autor deve ser preenchido.");
  }

  @Test
  void deveCriarInstanciaComSucesso() {

    Livro livro = new Livro("dummy", "dummy", RESUMO_COM_500_CHARS, new BigDecimal("23"), 100,
        mock(Categoria.class),
        mock(Autor.class));

    assertThat(livro).isNotNull();
    assertThat(livro.getIsbn()).isEqualTo("dummy");
    assertThat(livro.getTitulo()).isEqualTo("dummy");
    assertThat(livro.getResumo()).isEqualTo(RESUMO_COM_500_CHARS);
    assertThat(livro.getValor()).isEqualTo(valueOf(23));
    assertThat(livro.getNumeroPaginas()).isEqualTo(100);
    assertThat(livro.getCategoria()).isNotNull();
    assertThat(livro.getAutor()).isNotNull();
  }

}
