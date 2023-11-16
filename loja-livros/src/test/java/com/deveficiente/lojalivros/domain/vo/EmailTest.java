package com.deveficiente.lojalivros.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest {

  @ParameterizedTest
  @ValueSource(strings = {"renatodasilva@gmail.com", "user-name@domain.com", "user_name@domain.com",
      "user_name@domain.com.br"})
  void emailValido(String value) {
    Email email = new Email(value);
    assertThat(email).isNotNull();
  }

  @ValueSource(strings = {"username.@domain.com", ".user.name@domain.com", "user-name@domain.com.",
      "username@.com"})
  void emailNaoValido(String value) {
    Email email = new Email(value);
    assertThat(email).isNotNull();
  }
}