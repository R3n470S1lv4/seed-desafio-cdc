package com.deveficiente.lojalivros.controller.autor;

import com.deveficiente.lojalivros.domain.Autor;
import com.deveficiente.lojalivros.repository.AutorRepository;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class NovoAutorController {

  private final AutorRepository autorRepository;

  @PostMapping
  public ResponseEntity<NovoAutorResponse> cadastrar(
      @Valid @RequestBody NovoAutorRequest novoAutorRequest, UriComponentsBuilder uriBuilder) {
    Autor autor = novoAutorRequest.to();
    Autor savedAutor = autorRepository.save(autor);
    return ResponseEntity.ok().body(NovoAutorResponse.of(savedAutor));
  }

}
