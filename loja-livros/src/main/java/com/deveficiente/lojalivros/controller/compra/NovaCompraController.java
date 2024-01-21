package com.deveficiente.lojalivros.controller.compra;

import com.deveficiente.lojalivros.controller.compra.requests.NovaCompraRequest;
import com.deveficiente.lojalivros.controller.compra.responses.NovaCompraResponse;
import com.deveficiente.lojalivros.domain.Compra;
import com.deveficiente.lojalivros.repository.CompraRepository;
import javax.persistence.EntityManager;
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
@RequestMapping("/compras")
@RequiredArgsConstructor
public class NovaCompraController {

  private final CompraRepository compraRepository;
  private final EntityManager entityManager;
  private final EstadoPertencePaisValidator estadoPertencePaisValidator;
  private final EstadoObrigatorioValidator estadoObrigatorioValidator;


  //TODO VALIDAR CUPOM PELA DA DE VALIDADE
  @InitBinder
  public void init(WebDataBinder webDataBinder) {
    webDataBinder.addValidators(estadoPertencePaisValidator);
    webDataBinder.addValidators(estadoObrigatorioValidator);
  }

  @PostMapping
  public ResponseEntity<NovaCompraResponse> cadastrar(
      @Valid @RequestBody NovaCompraRequest novaCompraRequest) {
    Compra compra = compraRepository.save(novaCompraRequest.of(entityManager));

    return ResponseEntity.ok(NovaCompraResponse.of(compra));
  }

}
