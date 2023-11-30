package com.deveficiente.lojalivros.domain;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.length;

import java.math.BigDecimal;

public class ValidationUtils {

  public static boolean isValueLessThan(BigDecimal valor, BigDecimal minValue) {
    if (isNull(valor)) {
      return true;
    }
    return valor.compareTo(minValue) < 0;
  }

  public static boolean isValueLessThan(Integer value, Integer minValue) {
    return nonNull(value) && value.compareTo(minValue) < 0;
  }

  public static boolean isLengthGreaterThan(String value, int maxLength) {
    return length(value) <= maxLength;
  }

  public static boolean isLengthLessThan(String value, int minLength) {
    return length(value) < minLength;
  }

}
