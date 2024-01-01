package com.deveficiente.lojalivros.controller.compra.responses;

import com.deveficiente.lojalivros.domain.TipoDocumento;
import com.deveficiente.lojalivros.domain.vo.Documento;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DocumentoResponse {

  private TipoDocumento tipoDocumento;
  private String value;

  public static DocumentoResponse of(Documento documento) {
    return new DocumentoResponse(documento.getTipoDocumento(), documento.getValue());
  }
}
