package com.deveficiente.lojalivros.controller.categoria;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

  @PostMapping
  public ResponseEntity<Void> cadastrar(
      @Validated @RequestBody CategoriaRequest categoriaRequest) {

    return ResponseEntity.ok().build();
  }

}
