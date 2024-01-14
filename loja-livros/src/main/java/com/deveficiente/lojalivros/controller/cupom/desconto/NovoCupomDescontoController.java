package com.deveficiente.lojalivros.controller.cupom.desconto;

import com.deveficiente.lojalivros.domain.CupomDesconto;
import com.deveficiente.lojalivros.repository.CupomDescontoRepository;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cupons")
@RequiredArgsConstructor
public class NovoCupomDescontoController {

  private final CupomDescontoRepository cupomDescontoRepository;

  @PostMapping
  public ResponseEntity<NovoCupomDescontoResponse> cadastrar(
      @Valid @RequestBody NovoCupomRequest novoCupomRequest) {
    CupomDesconto savedCupomDesconto = cupomDescontoRepository.save(novoCupomRequest.of());
    return ResponseEntity.ok().body(NovoCupomDescontoResponse.of(savedCupomDesconto));
  }

}
