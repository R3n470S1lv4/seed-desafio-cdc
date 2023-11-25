package com.deveficiente.lojalivros.controller.categoria;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.deveficiente.lojalivros.domain.Categoria;
import com.deveficiente.lojalivros.repository.CategoriaRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;

@ExtendWith(MockitoExtension.class)
class ValidaCategoriaExstenteTest {

  @Mock
  private CategoriaRepository categoriaRepository;
  private ValidaCategoriaExstente validaCategoriaExstente;

  @BeforeEach
  void setUp() {
    validaCategoriaExstente = new ValidaCategoriaExstente(categoriaRepository);
  }

  @Test
  void deveValidarCategoriaQuandoCategoriaJaExistir() {
    String tecnologia = "tecnologia";

    when(categoriaRepository.findByNome(anyString())).thenReturn(
        Optional.of(mock(Categoria.class)));

    Errors errors = mock(Errors.class);
    NovaCategoriaRequest novaCategoriaRequest = mock(NovaCategoriaRequest.class);
    when(novaCategoriaRequest.getNome()).thenReturn(tecnologia);

    validaCategoriaExstente.validate(novaCategoriaRequest, errors);

    verify(categoriaRepository).findByNome(eq(tecnologia));
    verify(errors).rejectValue(eq("nome"), any(), anyString());
  }

  @Test
  void naoDeveValidarCategoria() {
    String tecnologia = "tecnologia";

    when(categoriaRepository.findByNome(anyString())).thenReturn(
        Optional.empty());

    Errors errors = mock(Errors.class);
    NovaCategoriaRequest novaCategoriaRequest = mock(NovaCategoriaRequest.class);
    when(novaCategoriaRequest.getNome()).thenReturn(tecnologia);

    validaCategoriaExstente.validate(novaCategoriaRequest, errors);

    verify(categoriaRepository).findByNome(eq(tecnologia));
  }
}