package com.deveficiente.lojalivros.controller.compra.responses;

import com.deveficiente.lojalivros.domain.vo.Telefone;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TelefoneResponse {

  @NotBlank
  private String numero;

  public static TelefoneResponse of(Telefone telefone) {
    return TelefoneResponse.builder()
        .numero(telefone.getNumero())
        .build();
  }
}
