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

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

  private final AutorRepository autorRepository;

  @PostMapping
  public ResponseEntity<Void> cadastrar(@Valid @RequestBody NovoAutorRequest novoAutorRequest) {
    Autor autor = novoAutorRequest.to();
    autorRepository.save(autor);
    return ResponseEntity.ok().build();
  }

}
