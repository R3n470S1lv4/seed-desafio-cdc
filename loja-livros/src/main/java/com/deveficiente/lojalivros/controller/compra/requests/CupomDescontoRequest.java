package com.deveficiente.lojalivros.controller.compra.requests;

import static com.deveficiente.lojalivros.domain.Precondition.requireNonNull;

import com.deveficiente.lojalivros.controller.annotations.EntityExists;
import com.deveficiente.lojalivros.domain.CupomDesconto;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CupomDescontoRequest {

  @NotBlank
  @EntityExists(domainClass = CupomDesconto.class, fieldName = "codigo", message = "Cupom de desconto nao encontrado.")
  private String codigo;

  public CupomDesconto of(EntityManager entityManager) {
    CupomDesconto cupomDesconto = entityManager.find(CupomDesconto.class, codigo);
    return requireNonNull(cupomDesconto, "Cupom de desconto nao encontrado.").take();
  }
}
