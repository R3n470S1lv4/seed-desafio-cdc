package com.deveficiente.lojalivros.controller.categoria;

import static java.text.MessageFormat.format;

import com.deveficiente.lojalivros.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ValidaCategoriaExstente implements Validator {

  private final CategoriaRepository categoriaRepository;

  @Override
  public boolean supports(Class<?> clazz) {
    return NovaCategoriaRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    NovaCategoriaRequest novaCategoriaRequest = (NovaCategoriaRequest) target;

    categoriaRepository.findByNome(novaCategoriaRequest.getNome())
        .ifPresent(categoria -> errors.rejectValue("nome", null,
            format("A categoria '{0}' já está cadastrada.",
                categoria.getNome())));

  }
}
