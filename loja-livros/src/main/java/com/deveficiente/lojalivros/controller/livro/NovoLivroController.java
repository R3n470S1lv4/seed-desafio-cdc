package com.deveficiente.lojalivros.controller.livro;

import com.deveficiente.lojalivros.domain.Livro;
import com.deveficiente.lojalivros.repository.AutorRepository;
import com.deveficiente.lojalivros.repository.CategoriaRepository;
import com.deveficiente.lojalivros.repository.LivroRepository;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class NovoLivroController {

  private final LivroRepository livroRepository;
  private final AutorRepository autorRepository;
  private final CategoriaRepository categoriaRepository;

  @PostMapping
  public ResponseEntity<NovoLivroResponse> cadastrar(
      @Valid @RequestBody NovoLivroRequest novoLivroRequest) {
    Livro livro = novoLivroRequest.to(autorRepository, categoriaRepository);
    Livro savedLivro = livroRepository.save(livro);
    return ResponseEntity.ok().body(NovoLivroResponse.of(savedLivro));
  }

}
