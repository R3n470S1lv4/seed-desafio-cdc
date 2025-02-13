package com.deveficiente.lojalivros.controller.annotations;

import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.deveficiente.lojalivros.domain.exceptions.PreconditionException;
import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityExistsValidator implements ConstraintValidator<EntityExists, Object> {

  private final EntityManager entityManager;
  private String fieldName;
  private Class<?> klass;
  private String message;

  @Override
  public void initialize(EntityExists params) {
    klass = params.domainClass();
    message = params.message();
    fieldName = camelCaseToSnake(requireNonNull(params.fieldName()));
  }

  private String camelCaseToSnake(String value) {
    return value.replaceAll("([A-Z][a-z])", "_$1");
  }

  private String requireNonNull(String value) {
    if (isBlank(value)) {
      throw new PreconditionException(
          format("O nome do campo {0} deve ser o mesmo declarado na entidade: {1}", value,
              klass.getName()));
    }
    return value;
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    constraintValidatorContext.disableDefaultConstraintViolation();

    if (isNull(value)) {
      return true;
    }

    if (isNotExists(value)) {
      buildMessage(constraintValidatorContext, message, value.toString());
      return false;
    }
    return true;
  }

  private boolean isNotExists(Object value) {
    return entityManager.createQuery(
            format("SELECT 1 FROM {0} WHERE UPPER({1}) = UPPER(:value)", klass.getName(), fieldName))
        .setParameter("value", value)
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
