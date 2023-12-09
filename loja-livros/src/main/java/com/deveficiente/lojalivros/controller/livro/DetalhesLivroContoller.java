package com.deveficiente.lojalivros.controller.livro;

import com.deveficiente.lojalivros.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class DetalhesLivroContoller {

  private final LivroRepository livroRepository;

  @GetMapping("/{id}")
  public ResponseEntity<DetalhesLivroResponse> detalhes(@PathVariable String id) {
    return livroRepository.findById(
            id)
        .map(DetalhesLivroResponse::of)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

}
