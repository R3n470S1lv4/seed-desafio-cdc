package com.deveficiente.lojalivros.domain;

import static java.math.BigDecimal.valueOf;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

import com.deveficiente.lojalivros.domain.exceptions.PreConditionException;
import java.math.BigDecimal;
import java.time.LocalDate;
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

  public static final String RESUMO_COM_501_CHARS = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce faucibus augue a risus commodo eleifend. Pellentesque tincidunt laoreet diam vitae luctus. Aenean nulla justo, vestibulum ut leo eu, varius finibus lectus. In ac velit lectus. Aliquam tempor tempor dolor, sed facilisis velit posuere eget. Vestibulum facilisis felis nulla, a egestas eros luctus sed. Sed accumsan, mi vel posuere suscipit, ipsum nisi volutpat purus, tempus vulputate tellus libero vitae tellus. Aliquam auctor lectus in. x";
  public static final String RESUMO_COM_500_CHARS = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce faucibus augue a risus commodo eleifend. Pellentesque tincidunt laoreet diam vitae luctus. Aenean nulla justo, vestibulum ut leo eu, varius finibus lectus. In ac velit lectus. Aliquam tempor tempor dolor, sed facilisis velit posuere eget. Vestibulum facilisis felis nulla, a egestas eros luctus sed. Sed accumsan, mi vel posuere suscipit, ipsum nisi volutpat purus, tempus vulputate tellus libero vitae tellus. Aliquam auctor lectus in.";
  public static final LocalDate DATA_PUBLICACAO_FUTURO = now().plusDays(1);

  @ParameterizedTest
  @NullAndEmptySource()
  void deveValidarISBNObrigatorio(String isbn) {
    assertThatThrownBy(() ->
        new Livro(null, "dummy", "dummy", "dummy", new BigDecimal("23"), 100, now(),
            mock(Categoria.class),
            mock(Autor.class))
    ).isInstanceOf(PreConditionException.class)
        .hasMessage("O campo ISBN deve ser preenchido.");
  }

  @ParameterizedTest
  @NullAndEmptySource()
  void deveValidarTituloObrigatorio(String titulo) {
    assertThatThrownBy(() ->
        new Livro("dummy", titulo, "dummy", "dummy", new BigDecimal("23"), 100, now(),
            mock(Categoria.class),
            mock(Autor.class))
    ).isInstanceOf(PreConditionException.class)
        .hasMessage("O campo TITULO deve ser preenchido.");
  }

  @ParameterizedTest
  @NullAndEmptySource()
  @ValueSource(strings = RESUMO_COM_501_CHARS)
  void deveValidarResumoObrigatorio(String resumo) {
    assertThatThrownBy(() ->
        new Livro("dummy", "dummy", resumo, "dummy", new BigDecimal("23"), 100, now(),
            mock(Categoria.class),
            mock(Autor.class))
    ).isInstanceOf(PreConditionException.class)
        .hasMessage("O comprimento do campo RESUMO deve ser entre 1 e 500.");
  }


  @ParameterizedTest
  @MethodSource("provideInvalidValuesForFieldValor")
  void deveValidarValorObrigatorio(BigDecimal valor) {
    assertThatThrownBy(() ->
        new Livro("dummy", "dummy", RESUMO_COM_500_CHARS, "dummy", valor, 100, now(),
            mock(Categoria.class),
            mock(Autor.class))
    ).isInstanceOf(PreConditionException.class)
        .hasMessage("O campo VALOR deve ser preenchido. E o valor minimo é 20.");
  }

  public static Stream<Arguments> provideInvalidValuesForFieldValor() {
    return Stream.of(Arguments.of(valueOf(19)),
        Arguments.of(valueOf(15)), Arguments.of(valueOf(-1)), Arguments.of(valueOf(0)));
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 0, 10, 20, 99})
  void deveValidarNumeroPaginasObrigatorio(int numeroPaginas) {
    assertThatThrownBy(() ->
        new Livro("dummy", "dummy", RESUMO_COM_500_CHARS, "dummy", valueOf(20), numeroPaginas,
            now(),
            mock(Categoria.class),
            mock(Autor.class))
    ).isInstanceOf(PreConditionException.class)
        .hasMessage(
            "O campo NUMERO DE PAGINAS deve ser preenchido. E deve possuir no minimo 20 paginas.");
  }

  @Test
  void deveValidarCategoriaObrigatoria() {
    assertThatThrownBy(() ->
        new Livro("dummy", "dummy", RESUMO_COM_500_CHARS, "dummy", valueOf(20), 100,
            DATA_PUBLICACAO_FUTURO,
            null,
            mock(Autor.class))
    ).isInstanceOf(PreConditionException.class)
        .hasMessage(
            "O campo CATEGORIA deve ser preenchido.");
  }

  @Test
  void deveValidarAutorObrigatoria() {
    assertThatThrownBy(() ->
        new Livro("dummy", "dummy", RESUMO_COM_500_CHARS, "dummy", valueOf(20), 100,
            DATA_PUBLICACAO_FUTURO,
            mock(Categoria.class),
            null)
    ).isInstanceOf(PreConditionException.class)
        .hasMessage(
            "O campo Autor deve ser preenchido.");
  }

  @Test
  void deveCriarInstanciaComSucesso() {
    Livro livro = new Livro("dummy", "dummy", RESUMO_COM_500_CHARS, "dummy", valueOf(23),
        100, DATA_PUBLICACAO_FUTURO,
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

  @Test
  void deveCriarInstanciaComSucessoComBuilder() {
    Livro livro = Livro.builder()
        .isbn("dummy")
        .titulo("dummy")
        .resumo(RESUMO_COM_500_CHARS)
        .valor(valueOf(23))
        .numeroPaginas(100)
        .dataPublicacao(DATA_PUBLICACAO_FUTURO)
        .categoria(mock(Categoria.class))
        .autor(mock(Autor.class))
        .sumario("dummy")
        .build();

    assertThat(livro).isNotNull();
    assertThat(livro.getIsbn()).isEqualTo("dummy");
    assertThat(livro.getTitulo()).isEqualTo("dummy");
    assertThat(livro.getResumo()).isEqualTo(RESUMO_COM_500_CHARS);
    assertThat(livro.getValor()).isEqualTo(valueOf(23));
    assertThat(livro.getNumeroPaginas()).isEqualTo(100);
    assertThat(livro.getCategoria()).isNotNull();
    assertThat(livro.getAutor()).isNotNull();
  }

  @ParameterizedTest
  @MethodSource("provideDate")
  void naoDeveAtulizarDtPublicao(LocalDate value) {
    assertThatThrownBy(
        () -> new Livro("dummy", "dummy", RESUMO_COM_500_CHARS, "dummy", new BigDecimal("23"),
            100, value, mock(Categoria.class), mock(Autor.class))).isInstanceOf(
        PreConditionException.class);
  }

  public static Stream<Arguments> provideDate() {
    return Stream.of(
        Arguments.of(now().minusDays(1)),
        Arguments.of(now())
    );
  }
}
