package com.deveficiente.lojalivros.controller.categoria;

import com.deveficiente.lojalivros.domain.Categoria;
import com.deveficiente.lojalivros.repository.CategoriaRepository;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

  private final ValidaCategoriaExstente validaCategoriaExstente;
  private final CategoriaRepository categoriaRepository;

  @InitBinder
  public void init(WebDataBinder webDataBinder) {
    webDataBinder.addValidators(validaCategoriaExstente);
  }

  @PostMapping
  public ResponseEntity<Void> cadastrar(
      @Valid @RequestBody NovaCategoriaRequest novaCategoriaRequest) {
    Categoria categoria = novaCategoriaRequest.of();
    categoriaRepository.save(categoria);
    return ResponseEntity.ok().build();
  }

}