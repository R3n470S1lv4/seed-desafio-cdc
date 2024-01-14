package com.deveficiente.lojalivros.controller.cupom.desconto;

import com.deveficiente.lojalivros.controller.annotations.UniqueValue;
import com.deveficiente.lojalivros.domain.CupomDesconto;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NovoCupomRequest {

  @UniqueValue(domainClass = CupomDesconto.class, fieldName = "codigo", message = "O cupom {0} já foi cadastrado.")
  @NotBlank
  private final String codigo;
  @NotNull
  @Positive
  private final BigDecimal percentual;
  @NotNull
  @Future
  private final LocalDate validade;

  public CupomDesconto of() {
    return CupomDesconto.builder()
        .codigo(codigo)
        .percentual(percentual)
        .validade(validade)
        .build();
  }
}
