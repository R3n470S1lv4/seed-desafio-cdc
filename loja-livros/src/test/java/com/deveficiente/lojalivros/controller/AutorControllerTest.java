package com.deveficiente.lojalivros.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.deveficiente.lojalivros.repository.AutorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(AutorController.class)
class AutorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AutorRepository autorRepository;

  @Test
  void deveCadastrarUmNovoAutor() throws Exception {
    mockMvc.perform(post("/autores")
            .contentType(APPLICATION_JSON)
            .content(
                "{\"nome\": \"renato\", \"email\": \"renato@gmail.com\", \"descricao\": \"dummy dummy\"}")
        )
        .andExpect(status().isOk());
  }

  @Test
  void deveTratarUmaDomainException() throws Exception {
    mockMvc.perform(post("/autores")
            .contentType(APPLICATION_JSON)
            .content(
                "{\"nome\": \"renato\", \"email\": \"renato.gmail.com\", \"descricao\": \"dummy dummy\"}")
        ).andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().json("{\"Message\":\"O campo Email tem um formato invalido.\"}"));
  }

}