package com.deveficiente.lojalivros.controller.livro;

import com.deveficiente.lojalivros.controller.annotations.EntityExists;
import com.deveficiente.lojalivros.controller.annotations.UniqueValue;
import com.deveficiente.lojalivros.domain.Autor;
import com.deveficiente.lojalivros.domain.Categoria;
import com.deveficiente.lojalivros.domain.Livro;
import com.deveficiente.lojalivros.domain.exceptions.PreConditionException;
import com.deveficiente.lojalivros.repository.AutorRepository;
import com.deveficiente.lojalivros.repository.CategoriaRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class NovoLivroRequest {

  @NotBlank
  @UniqueValue(fieldName = "isbn", domainClass = Livro.class, message = "Ja existe um livro cadastrado com o mesmo isbn: {0}")
  private String isbn;
  @NotBlank
  @UniqueValue(fieldName = "titulo", domainClass = Livro.class, message = "Ja existe um livro cadastrado com o mesmo titulo: {0}")
  private String titulo;
  @NotBlank
  @Length(min = 1, max = 500)
  private String resumo;
  private String sumario;
  @DecimalMin("20")
  @NotNull
  private BigDecimal valor;
  @NotNull
  @Min(100)
  private Integer numeroPaginas;
  @Future
  @NotNull
  private LocalDate dataPublicacao;
  @NotBlank
  @EntityExists(fieldName = "categoriaId", domainClass = Categoria.class, message = "Categoria nao encontrada com o id informado: {0}")
  private String categoriaId;
  @NotBlank
  @EntityExists(fieldName = "autorId", domainClass = Autor.class, message = "Autor nao encontrado com o id informado: {0}")
  private String autorId;

  public Livro to(AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
    Autor autor = autorRepository.findById(autorId)
        .orElseThrow(() -> new PreConditionException(
            "Autor {0} nao encontrado.", autorId));

    Categoria categoria = categoriaRepository.findById(categoriaId)
        .orElseThrow(() -> new PreConditionException(
            "Categoria {0} nao encontrada.", autorId));

    return new Livro(isbn, titulo, resumo, sumario, valor, numeroPaginas, dataPublicacao, categoria,
        autor);
  }
}
