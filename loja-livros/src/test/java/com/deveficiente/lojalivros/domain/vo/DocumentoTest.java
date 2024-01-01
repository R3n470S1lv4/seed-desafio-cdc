package com.deveficiente.lojalivros.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.deveficiente.lojalivros.domain.TipoDocumento;
import com.deveficiente.lojalivros.domain.exceptions.PreconditionException;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DocumentoTest {

  public static Stream<Arguments> provideValidDocument() {
    return Stream.of(
        Arguments.of(TipoDocumento.CPF, "34970918805"),
        Arguments.of(TipoDocumento.CPF, "349.709.188-05"),
        Arguments.of(TipoDocumento.CNPJ, "63062953000102"),
        Arguments.of(TipoDocumento.CNPJ, "63.062.953.0001/02")
    );
  }

  public static Stream<Arguments> provideInvalidDocument() {
    return Stream.of(
        Arguments.of(TipoDocumento.CPF, "34970918809"),
        Arguments.of(TipoDocumento.CPF, "349.709.188-09"),
        Arguments.of(TipoDocumento.CNPJ, "63062953000107"),
        Arguments.of(TipoDocumento.CNPJ, "63.062.953.0001/07")
    );
  }

  @ParameterizedTest
  @MethodSource("provideValidDocument")
  void shouldCreateAValidDocument(TipoDocumento tipoDocumento, String number) {
    Documento documento = new Documento(tipoDocumento, number);

    assertThat(documento).isNotNull();
    assertThat(documento.getValue()).isNotNull().isEqualTo(number);
  }

  @ParameterizedTest
  @MethodSource("provideInvalidDocument")
  void shouldCreateAInvalidDocument(TipoDocumento tipoDocumento, String number) {
    assertThatThrownBy(() -> new Documento(tipoDocumento, number))
        .isExactlyInstanceOf(PreconditionException.class);
  }

}