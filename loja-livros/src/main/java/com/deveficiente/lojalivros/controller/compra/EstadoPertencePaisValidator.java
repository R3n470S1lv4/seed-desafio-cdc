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

  @Override
  public boolean supports(Class<?> clazz) {
    return NovaCompraRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    NovaCompraRequest novaCompraRequest = (NovaCompraRequest) target;

    if (novaCompraRequest.hasNotEstado()) {
      return;
    }

    Pais pais = entityManager.find(Pais.class, novaCompraRequest.getPais());
    Estado estado = entityManager.find(Estado.class, novaCompraRequest.getEstado());

    if (estado.isNotOf(pais)) {
      errors.rejectValue("pessoa.endereco.estadoId", null,
          "O Estado nao pertence ao Pais.");
    }
  }
}
