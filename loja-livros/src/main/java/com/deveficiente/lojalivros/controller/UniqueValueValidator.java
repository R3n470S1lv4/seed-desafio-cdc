package com.deveficiente.lojalivros.controller;

import static java.text.MessageFormat.format;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

  private String fieldName;
  private Class<?> klass;
  private String message;
  private final EntityManager entityManager;

  public UniqueValueValidator(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void initialize(UniqueValue params) {
    fieldName = params.fieldName();
    klass = params.domainClass();
    message = params.message();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    constraintValidatorContext.disableDefaultConstraintViolation();
    constraintValidatorContext.buildConstraintViolationWithTemplate(
            format(message, value.toString()))
        .addConstraintViolation();

    return entityManager.createQuery(
            format("SELECT 1 FROM {0} WHERE {1} = :value", klass.getName(), fieldName))
        .setParameter("value", value)
        .getResultList()
        .isEmpty();
  }
}
