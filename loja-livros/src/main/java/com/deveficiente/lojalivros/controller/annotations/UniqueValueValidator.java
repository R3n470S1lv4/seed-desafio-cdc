package com.deveficiente.lojalivros.controller.annotations;

import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;

import com.deveficiente.lojalivros.domain.Precondition;
import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

  private final EntityManager entityManager;
  private String fieldName;
  private Class<?> klass;
  private String message;

  @Override
  public void initialize(UniqueValue params) {
    fieldName = params.fieldName();
    klass = params.domainClass();
    message = params.message();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    constraintValidatorContext.disableDefaultConstraintViolation();

    if (isNull(value)) {
      buildMessage(constraintValidatorContext, "O campo ''{0}'' deve ser preenchido.", fieldName);
      return false;
    }
    if (isNotUnique(value)) {
      buildMessage(constraintValidatorContext, message, value.toString());
      return false;
    }
    return true;
  }

  private boolean isNotUnique(Object value) {
    Precondition.requireNonNull(value).andNonBlank();

    return !entityManager.createQuery(
            format("SELECT 1 FROM {0} WHERE UPPER({1}) = UPPER(:value)", klass.getName(), fieldName))
        .setParameter("value", value.toString().toUpperCase())
        .getResultList()
        .isEmpty();
  }

  private void buildMessage(ConstraintValidatorContext constraintValidatorContext, String pattern,
      String fieldName) {
    constraintValidatorContext.buildConstraintViolationWithTemplate(
            format(pattern, fieldName))
        .addConstraintViolation();
  }
}
