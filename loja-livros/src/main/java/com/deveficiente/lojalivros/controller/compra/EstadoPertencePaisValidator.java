package com.deveficiente.lojalivros.controller.compra;

import com.deveficiente.lojalivros.controller.compra.requests.NovaCompraRequest;
import com.deveficiente.lojalivros.domain.Estado;
import com.deveficiente.lojalivros.domain.Pais;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class EstadoPertencePaisValidator implements Validator {

  private final EntityManager entityManager;
  private Errors errors;
  private NovaCompraRequest novaCompraRequest;

  @Override
  public boolean supports(Class<?> clazz) {
    return NovaCompraRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) {
      return;
    }

    this.errors = errors;
    this.novaCompraRequest = (NovaCompraRequest) target;

    validaEstadoPertencePais();
  }

  private void validaEstadoPertencePais() {
    if (novaCompraRequest.hasNotEstado()) {
      return;
    }

    Pais pais = entityManager.find(Pais.class, novaCompraRequest.getPaisId());
    Estado estado = entityManager.find(Estado.class, novaCompraRequest.getEstadoId());

    if (estado.isNotOf(pais)) {
      errors.rejectValue("pessoa.endereco.estadoId", null,
          "O Estado nao pertence ao Pais.");
    }
  }
}
