package com.deveficiente.lojalivros.controller.compra;

import com.deveficiente.lojalivros.domain.TipoDocumento;
import com.deveficiente.lojalivros.domain.vo.Documento;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DocumentoRequest {

  @NotNull
  private TipoDocumento tipoDocumento;
  @NotBlank
//  @CPF
//  @CNPJ
  private String value;

  public Documento of() {
    return new Documento(tipoDocumento, value);
  }
}
