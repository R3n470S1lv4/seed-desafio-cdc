package com.deveficiente.lojalivros.controller.autor;

import com.deveficiente.lojalivros.domain.Autor;
import com.deveficiente.lojalivros.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

  private final AutorRepository autorRepository;
  private final ValidaEmailExistente validaEmailExistente;

  @InitBinder
  public void init(WebDataBinder webDataBinder) {
    webDataBinder.addValidators(validaEmailExistente);
  }

  @PostMapping
  public ResponseEntity<Void> cadastrar(@Validated @RequestBody NovoAutorRequest novoAutorRequest) {
    Autor autor = novoAutorRequest.to();
    autorRepository.save(autor);
    return ResponseEntity.ok().build();
  }

}
