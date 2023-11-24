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
  void deveValidarCategoriaExistente() {
    String tecnologia = "tecnologia";

    when(categoriaRepository.findByNome(anyString())).thenReturn(
        Optional.of(mock(Categoria.class)));

    Errors errors = mock(Errors.class);
    CategoriaRequest categoriaRequest = mock(CategoriaRequest.class);
    when(categoriaRequest.getNome()).thenReturn(tecnologia);

    validaCategoriaExstente.validate(categoriaRequest, errors);

    verify(categoriaRepository).findByNome(eq(tecnologia));
    verify(errors).rejectValue(eq("nome"), any(), anyString());
  }

  @Test
  void naoDeveValidarCategoriaExistente() {
    String tecnologia = "tecnologia";

    when(categoriaRepository.findByNome(anyString())).thenReturn(
        Optional.empty());

    Errors errors = mock(Errors.class);
    CategoriaRequest categoriaRequest = mock(CategoriaRequest.class);
    when(categoriaRequest.getNome()).thenReturn(tecnologia);

    validaCategoriaExstente.validate(categoriaRequest, errors);

    verify(categoriaRepository).findByNome(eq(tecnologia));
  }
}