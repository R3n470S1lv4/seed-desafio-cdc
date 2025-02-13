package com.deveficiente.lojalivros.domain;


import static com.deveficiente.lojalivros.domain.Precondition.requireNonNull;
import static java.time.LocalDate.now;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class CupomDesconto {

  @Id
  @Column(name = "cupom_desconto_codigo")
  private String codigo;
  private BigDecimal percentual;
  private LocalDate validade;

  private CupomDesconto() {
  }

  @Builder
  public CupomDesconto(String codigo, BigDecimal percentual, LocalDate validade) {
    this.codigo = requireNonNull(codigo).nonBlank().take();
    this.percentual = requireNonNull(percentual)
        .isNotLessThan(1)
        .take();
    this.validade = requireNonNull(validade).isAfter(now()).take();
  }

  public void isValido() {
    requireNonNull(this.validade, "Cupom de desconto expirado.").isAfter(now());
  }

}
