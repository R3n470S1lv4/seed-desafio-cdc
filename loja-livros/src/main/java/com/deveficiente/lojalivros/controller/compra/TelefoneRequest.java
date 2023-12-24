package com.deveficiente.lojalivros.controller.compra;

import com.deveficiente.lojalivros.domain.vo.Telefone;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TelefoneRequest {

  @NotBlank
  private String numero;

  public Telefone of() {
    return new Telefone(numero);
  }
}
