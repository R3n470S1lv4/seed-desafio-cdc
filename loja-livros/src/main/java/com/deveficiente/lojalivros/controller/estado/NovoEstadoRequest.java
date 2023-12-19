package com.deveficiente.lojalivros.controller.estado;

import com.deveficiente.lojalivros.controller.annotations.EntityExists;
import com.deveficiente.lojalivros.controller.annotations.UniqueValue;
import com.deveficiente.lojalivros.domain.Estado;
import com.deveficiente.lojalivros.domain.Pais;
import com.deveficiente.lojalivros.domain.PreconditionException;
import com.deveficiente.lojalivros.repository.PaisRepository;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NovoEstadoRequest {

  @NotBlank
  @UniqueValue(domainClass = Estado.class, fieldName = "nome", message = "Ja existe um cadastro com o mesmo nome: {0}")
  private String nome;
  @EntityExists(domainClass = Pais.class, fieldName = "nome", message = "O Pais {0} nao foi cadastrado.")
  @NotBlank
  private String pais;

  public Estado of(NovoEstadoRequest estado, PaisRepository paisRepository) {
    Pais pais = paisRepository.findByNomeIgnoreCase(estado.pais)
        .orElseThrow(() -> new PreconditionException("O Pais {0} nao foi cadastrado.",
            estado.pais));

    return new Estado(estado.getNome(), pais);
  }
}
