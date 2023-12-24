package com.deveficiente.lojalivros.domain.vo;

import static com.deveficiente.lojalivros.domain.Precondition.requireNonNull;

import com.deveficiente.lojalivros.domain.TipoDocumento;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Documento {

  private TipoDocumento tipoDocumento;
  //TODO VALIDAR
  @Column(name = "DOCUMENTO")
  private String value;

  /**
   * @deprecated Nao use esse construtor, ele so existe por causa do ORM
   */
  @Deprecated
  private Documento() {
  }

  public Documento(TipoDocumento tipoDocumento, String value) {
    this.tipoDocumento = requireNonNull(tipoDocumento).take();
    this.value = requireNonNull(value).andNonBlank().take();
  }
}
