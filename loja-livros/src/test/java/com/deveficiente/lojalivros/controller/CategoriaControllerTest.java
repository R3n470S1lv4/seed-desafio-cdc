package com.deveficiente.lojalivros.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.deveficiente.lojalivros.controller.categoria.CategoriaController;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CategoriaController.class)
class CategoriaControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @SneakyThrows
  void deveCadastrarUmaNovaCategoria() {
    mockMvc.perform(post("/categorias")
            .contentType(APPLICATION_JSON)
            .content("{\"nome\":\"teste\"}")
        )
        .andExpect(status().isOk());
  }
}