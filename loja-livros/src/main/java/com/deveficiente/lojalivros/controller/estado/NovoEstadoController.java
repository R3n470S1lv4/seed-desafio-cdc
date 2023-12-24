package com.deveficiente.lojalivros.controller.estado;


import com.deveficiente.lojalivros.domain.Estado;
import com.deveficiente.lojalivros.repository.EstadoRepository;
import com.deveficiente.lojalivros.repository.PaisRepository;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor
public class NovoEstadoController {

  private final EstadoRepository estadoRepository;
  private final PaisRepository paisRepository;

  @PostMapping
  public ResponseEntity<NovoEstadoResponse> cadastrar(
      @Valid @RequestBody NovoEstadoRequest novoEstadoRequest) {
    Estado novoEstado = estadoRepository.save(
        novoEstadoRequest.of(paisRepository));
    return ResponseEntity.ok().body(NovoEstadoResponse.of(novoEstado));
  }

}
