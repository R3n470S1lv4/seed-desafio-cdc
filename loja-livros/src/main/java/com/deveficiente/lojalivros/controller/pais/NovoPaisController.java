package com.deveficiente.lojalivros.controller.pais;

import com.deveficiente.lojalivros.domain.Pais;
import com.deveficiente.lojalivros.repository.PaisRepository;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pais")
@RequiredArgsConstructor
public class NovoPaisController {

  private final PaisRepository paisRepository;

  @PostMapping
  public ResponseEntity<NovoPaisResponse> cadastrar(
      @Valid @RequestBody NovoPaisRequest novoPaisRequest) {
    Pais novoPais = paisRepository.save(novoPaisRequest.of());
    return ResponseEntity.ok().body(NovoPaisResponse.of(novoPais));
  }

}
