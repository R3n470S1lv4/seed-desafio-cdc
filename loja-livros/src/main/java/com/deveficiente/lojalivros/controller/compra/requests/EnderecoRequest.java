package com.deveficiente.lojalivros.controller.compra.requests;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNoneBlank;

import com.deveficiente.lojalivros.controller.annotations.EntityExists;
import com.deveficiente.lojalivros.domain.Estado;
import com.deveficiente.lojalivros.domain.Pais;
import com.deveficiente.lojalivros.domain.exceptions.PosconditionException;
import com.deveficiente.lojalivros.domain.vo.Endereco;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EnderecoRequest {

  @NotBlank
  private String logradouro;
  @NotBlank
  private String bairro;
  @NotBlank
  private String cidade;
  //TODO se o país tiver estados, um estado precisa ser selecionado
  @EntityExists(domainClass = Estado.class, fieldName = "estado_id", message = "O Estado {0} nao foi cadastrado.")
  private String estadoId;
  @NotBlank
  @EntityExists(domainClass = Pais.class, fieldName = "pais_id", message = "O Pais {0} nao foi cadastrado.")
  private String paisId;
  @NotBlank
  private String complemento;
  @NotBlank
  private String cep;

  public Endereco of(EntityManager entityManager) {
    Pais pais = ofNullable(entityManager.find(Pais.class, paisId))
        .orElseThrow(() -> new PosconditionException("O Pais {0} nao foi cadastrado.", paisId));

    Endereco endereco = Endereco.builder()
        .logradouro(logradouro)
        .bairro(bairro)
        .cidade(cidade)
        .pais(pais)
        .complemento(complemento)
        .cep(cep)
        .build();

    if (isNoneBlank(estadoId)) {
      Estado estado = ofNullable(entityManager.find(Estado.class, estadoId))
          .orElseThrow(
              () -> new PosconditionException("O Estado {0} nao foi cadastrado.", estadoId));

      return endereco.withEstado(estado);
    }

    return endereco;
  }
}
