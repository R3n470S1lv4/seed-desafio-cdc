package com.deveficiente.lojalivros.domain.vo;

import static com.deveficiente.lojalivros.domain.Precondition.requireNonNull;
import static java.text.MessageFormat.format;

import com.deveficiente.lojalivros.domain.Estado;
import com.deveficiente.lojalivros.domain.Pais;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
public class Endereco {

  private String logradouro;
  private String bairro;
  private String cidade;
  @OneToOne
  @JoinColumn(name = "estado_id")
  private Estado estado;
  @OneToOne
  @JoinColumn(name = "pais_id")
  private Pais pais;
  private String complemento;
  private String cep;

  private Endereco() {
  }

  @Builder
  public Endereco(String logradouro, String bairro, String cidade, Pais pais, String complemento,
      String cep) {
    this.logradouro = requireNonNull(logradouro).nonBlank().take();
    this.bairro = requireNonNull(bairro).nonBlank().take();
    this.cidade = requireNonNull(cidade).nonBlank().take();
    this.pais = requireNonNull(pais).take();
    this.complemento = requireNonNull(complemento).take();
    this.cep = requireNonNull(cep).take();
  }

  public Endereco(String logradouro, String bairro, String cidade, Pais pais, String complemento,
      String cep, Estado estado) {
    this(logradouro, bairro, cidade, pais, complemento, cep);
    this.estado = requireNonNull(estado)
        .isTrue(estado.isOf(pais),
            format("O estado {0} nao pertence ao Pais {1}", estado.getNome(), pais.getNome()))
        .take();
  }

  public Endereco withEstado(Estado estado) {
    return new Endereco(this.logradouro, this.bairro, this.cidade, this.pais, this.complemento,
        this.cep, estado);
  }

  public String getEstadoId() {
    return estado.getId();
  }

  public boolean hasEstado() {
    return Objects.nonNull(this.estado);
  }

  public String getPaisId() {
    return pais.getId();
  }
}
