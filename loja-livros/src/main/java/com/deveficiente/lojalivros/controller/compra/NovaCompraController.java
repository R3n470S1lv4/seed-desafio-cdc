package com.deveficiente.lojalivros.controller.compra;

import com.deveficiente.lojalivros.repository.PagamentoRepository;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class NovaCompraController {

  private final PagamentoRepository pagamentoRepository;
  private final EntityManager entityManager;
  private final EstadoPertencePaisValidator estadoPertencePaisValidator;

  public void init(WebDataBinder webDataBinder) {
    webDataBinder.addValidators(estadoPertencePaisValidator);
  }

  @PostMapping
  public ResponseEntity<Void> cadastrar(@Valid @RequestBody NovaCompraRequest novaCompraRequest) {
    pagamentoRepository.save(novaCompraRequest.of(entityManager));

    return ResponseEntity.ok().build();
  }

}
