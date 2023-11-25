package com.deveficiente.lojalivros.controller.autor;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.deveficiente.lojalivros.domain.Autor;
import com.deveficiente.lojalivros.repository.AutorRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(AutorController.class)
@ExtendWith(MockitoExtension.class)
class AutorControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private AutorRepository autorRepository;
  @MockBean
  private ValidaEmailAutorExistente validaEmailAutorExistente;
  @InjectMocks
  private AutorController autorController;

  @BeforeEach
  void setUp() {
    when(validaEmailAutorExistente.supports(any())).thenReturn(true);
    when(autorRepository.save(any())).thenReturn(mock(Autor.class));
  }

  @Test
  @SneakyThrows
  void deveCadastrarUmNovoAutor() {
    mockMvc.perform(post("/autores")
            .contentType(APPLICATION_JSON)
            .content(
                "{\"nome\": \"renato\", \"email\": \"renato@gmail.com\", \"descricao\": \"dummy dummy\"}")
        )
        .andExpect(status().isOk());

    verify(validaEmailAutorExistente).validate(any(NovoAutorRequest.class), any());
    verify(autorRepository).save(any(Autor.class));
  }

  @Test
  @SneakyThrows
  void deveTratarUmaDomainException() {
    mockMvc.perform(post("/autores")
            .contentType(APPLICATION_JSON)
            .content(
                "{\"nome\": \"renato\", \"email\": \"renato.gmail.com\", \"descricao\": \"dummy dummy\"}")
        ).andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().json("{\"message\":\"O campo Email tem um formato invalido.\"}"));

    verify(validaEmailAutorExistente).validate(any(NovoAutorRequest.class), any());
    verify(autorRepository, never()).save(any(Autor.class));
  }

  @Test
  @SneakyThrows
  void deveValidarCampoNomeNaoInfomado() {
    mockMvc.perform(post("/autores")
            .contentType(APPLICATION_JSON)
            .content(
                "{\"nome\": \"\", \"email\": \"renato.gmail.com\", \"descricao\": \"dummy dummy\"}")
        ).andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.status", is(400)))
        .andExpect(jsonPath("$.error", is("Bad Request")))
        .andExpect(jsonPath("$.message", is("Validation failed")))
        .andExpect(jsonPath("$.errors.[*].fieldName", containsInAnyOrder("nome")))
        .andExpect(jsonPath("$.errors.[*].message", containsInAnyOrder("must not be blank")));

    verify(validaEmailAutorExistente).validate(any(NovoAutorRequest.class), any());
    verify(autorRepository, never()).save(any(Autor.class));
  }

}