package com.deveficiente.lojalivros.controller.categoria;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.deveficiente.lojalivros.domain.Categoria;
import com.deveficiente.lojalivros.repository.CategoriaRepository;
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

@WebMvcTest(CategoriaController.class)
@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CategoriaRepository categoriaRepository;

  @InjectMocks
  private CategoriaController categoriaController;

  @BeforeEach
  void setUp() {
    when(categoriaRepository.save(any())).thenReturn(mock(Categoria.class));
  }

  @Test
  @SneakyThrows
  void deveCadastrarUmaNovaCategoria() {
    mockMvc.perform(post("/categorias")
            .contentType(APPLICATION_JSON)
            .content("{\"nome\":\"tecnologia\"}")
        )
        .andExpect(status().isOk());
    verify(categoriaRepository).save(any(Categoria.class));
  }

  @Test
  @SneakyThrows
  void deveValidarQuandoNomeNaoInformado() {
    mockMvc.perform(post("/categorias")
            .contentType(APPLICATION_JSON)
            .content("{\"nome\":\"\"}"))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.status", is(400)))
        .andExpect(jsonPath("$.error", is("Bad Request")))
        .andExpect(jsonPath("$.message", is("Validation failed")))
        .andExpect(jsonPath("$.errors.[*].fieldName", containsInAnyOrder("nome")))
        .andExpect(jsonPath("$.errors.[*].message", containsInAnyOrder("must not be blank")));

    verify(categoriaRepository, never()).save(any(Categoria.class));
  }
}