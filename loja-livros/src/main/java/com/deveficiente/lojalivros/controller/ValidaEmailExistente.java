package com.deveficiente.lojalivros.controller;

import static java.text.MessageFormat.format;

import com.deveficiente.lojalivros.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ValidaEmailExistente implements Validator {

  private final AutorRepository autorRepository;

  @Override
  public boolean supports(Class<?> clazz) {
    return AutorRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    AutorRequest autorRequest = (AutorRequest) target;
    autorRepository.findByEmailValue(autorRequest.email())
        .ifPresent((email) -> errors.reject("email", null,
            format("Já existe um autor com esse endereço de email: {0}.", email)));

  }
}
