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
    return CategoriaRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    CategoriaRequest categoriaRequest = (CategoriaRequest) target;

    categoriaRepository.findByNome(categoriaRequest.getNome())
        .ifPresent(categoria -> errors.rejectValue("nome", null,
            format("Ja existe uma categoria cadastrada com o mesmo nome {0}.",
                categoria.getNome())));

  }
}
