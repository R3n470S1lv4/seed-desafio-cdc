package com.deveficiente.lojalivros.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.deveficiente.lojalivros.domain.exceptions.PreconditionException;
import org.junit.jupiter.api.Test;

class CategoriaTest {

  @Test
  void deveCriarUmaCategoria() {
    String tecnologia = "Tecnologia";
    Categoria categoria = new Categoria(tecnologia);

    assertThat(categoria).isNotNull();
    assertThat(categoria.getNome()).isNotNull().isEqualTo(tecnologia);
  }

  @Test
  void deveCriaCategoriaSemNome() {
    assertThatThrownBy(() -> new Categoria(null))
        .isExactlyInstanceOf(PreconditionException.class);
  }
}