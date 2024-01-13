package com.deveficiente.lojalivros.controller.compra;

import com.deveficiente.lojalivros.controller.compra.requests.NovaCompraRequest;
import com.deveficiente.lojalivros.domain.Pais;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class EstadoObrigatorioValidator implements Validator {

  private final EntityManager entityManager;

  @Override
  public boolean supports(Class<?> clazz) {
    return NovaCompraRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    NovaCompraRequest novaCompraRequest = (NovaCompraRequest) target;
    if (novaCompraRequest.hasNotEstado()) {
      Pais pais = entityManager.find(Pais.class, novaCompraRequest.getPaisId());
      if (pais.isEstadoMandatory()) {
        errors.rejectValue("pessoa.endereco.estadoId", null,
            "O Estado precisa ser informado para este Pais.");
      }
    }

  }
}
