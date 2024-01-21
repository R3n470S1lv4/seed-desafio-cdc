package com.deveficiente.lojalivros.controller.cupom.desconto;

import com.deveficiente.lojalivros.domain.CupomDesconto;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NovoCupomDescontoResponse {

  private final String codigo;
  private final BigDecimal percentual;
  private final LocalDate validade;

  public static NovoCupomDescontoResponse of(CupomDesconto cupomDesconto) {
    return NovoCupomDescontoResponse.builder()
        .codigo(cupomDesconto.getCodigo())
        .percentual(cupomDesconto.getPercentual())
        .validade(cupomDesconto.getValidade())
        .build();
  }

}
