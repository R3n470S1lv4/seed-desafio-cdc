package com.deveficiente.lojalivros.controller.autor;

import com.deveficiente.lojalivros.repository.AutorRepository;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class ListaAutoresController {

  private final AutorRepository autorRepository;
  private final EntityManager entityManager;

  @GetMapping
  public ResponseEntity<List<ListaAutoresResponse>> listAll() {
    List<ListaAutoresResponse> autores = autorRepository.findAll()
        .stream().map(ListaAutoresResponse::of)
        .toList();

    return ResponseEntity.ok().body(autores);
  }

}
