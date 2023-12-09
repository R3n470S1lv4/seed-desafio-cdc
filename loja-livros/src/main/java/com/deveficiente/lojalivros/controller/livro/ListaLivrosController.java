package com.deveficiente.lojalivros.controller.livro;

import com.deveficiente.lojalivros.repository.LivroRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class ListaLivrosController {

  private final LivroRepository livroRepository;

  @GetMapping
  public ResponseEntity<List<NovoLivroResponse>> list() {
    List<NovoLivroResponse> livros = livroRepository.findAll().stream().map(NovoLivroResponse::of)
        .toList();

    return ResponseEntity.ok().body(livros);
  }
}
