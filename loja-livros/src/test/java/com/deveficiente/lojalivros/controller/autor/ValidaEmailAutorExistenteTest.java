package com.deveficiente.lojalivros.controller.autor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.deveficiente.lojalivros.domain.Autor;
import com.deveficiente.lojalivros.repository.AutorRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;

@ExtendWith(MockitoExtension.class)
class ValidaEmailAutorExistenteTest {

  public static final String MAIL = "renta@gmail.com";
  @Mock
  private AutorRepository autorRepository;
  private ValidaEmailAutorExistente validaEmailAutorExistente;

  @BeforeEach
  void setUp() {
    validaEmailAutorExistente = new ValidaEmailAutorExistente(autorRepository);
  }

  @Test
  void deveRegistarDupliciadadeEmail() {
    NovoAutorRequest novoAutorRequest = mock(NovoAutorRequest.class);
    when(novoAutorRequest.getEmail()).thenReturn(MAIL);
    Errors errors = mock(Errors.class);

    when(autorRepository.findByEmailValue(MAIL)).thenReturn(Optional.of(mock(Autor.class)));

    validaEmailAutorExistente.validate(novoAutorRequest, errors);

    verify(autorRepository).findByEmailValue(eq(MAIL));
    verify(errors).rejectValue(eq("email"), any(), anyString());
  }

  @Test
  void naoDeveRegistarDupliciadadeEmail() {
    NovoAutorRequest novoAutorRequest = mock(NovoAutorRequest.class);
    when(novoAutorRequest.getEmail()).thenReturn(MAIL);
    Errors errors = mock(Errors.class);

    when(autorRepository.findByEmailValue(MAIL)).thenReturn(Optional.empty());

    validaEmailAutorExistente.validate(novoAutorRequest, errors);

    verify(autorRepository).findByEmailValue(eq(MAIL));
  }
}