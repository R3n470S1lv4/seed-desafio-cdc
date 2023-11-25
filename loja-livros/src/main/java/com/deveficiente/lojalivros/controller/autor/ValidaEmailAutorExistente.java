package com.deveficiente.lojalivros.controller.autor;

import static java.text.MessageFormat.format;

import com.deveficiente.lojalivros.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ValidaEmailAutorExistente implements Validator {

  private final AutorRepository autorRepository;

  @Override
  public boolean supports(Class<?> clazz) {
    return NovoAutorRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    NovoAutorRequest novoAutorRequest = (NovoAutorRequest) target;

    autorRepository.findByEmailValue(novoAutorRequest.getEmail())
        .ifPresent(email -> errors.rejectValue("email", null,
            format("Já existe um autor com esse endereço de email: {0}.", email)));

  }
}
