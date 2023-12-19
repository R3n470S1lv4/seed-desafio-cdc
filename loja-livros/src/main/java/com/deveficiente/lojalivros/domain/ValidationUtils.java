package com.deveficiente.lojalivros.domain;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.length;

import java.math.BigDecimal;

public class ValidationUtils {

  public static boolean isValueLessThan(Number value, Number minValue) {
    if (isNull(value)) {
      return true;
    }

    BigDecimal valueDecimal = new BigDecimal(value.toString());
    BigDecimal minValueDecimal = new BigDecimal(minValue.toString());

    return valueDecimal.compareTo(minValueDecimal) < 0;
  }

  public static boolean isLengthGreaterThan(String value, int maxLength) {
    return length(value) > maxLength;
  }

  public static boolean isLengthLessThan(String value, int minLength) {
    return length(value) < minLength;
  }

}
