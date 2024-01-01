package com.deveficiente.lojalivros.domain;

import static java.util.Objects.isNull;

import java.math.BigDecimal;

public class NumberValidationUtils {

  public static boolean isLessThan(Number value, Number minValue) {
    if (isNull(value)) {
      return true;
    }
    return toBigDecimal(value).compareTo(toBigDecimal(minValue)) < 0;
  }

  public static boolean isEquals(Number value, Number otherValue) {
    return toBigDecimal(value).compareTo(toBigDecimal(otherValue)) == 0;
  }

  public static boolean isNotEquals(Number value, Number otherValue) {
    return toBigDecimal(value).compareTo(toBigDecimal(otherValue)) != 0;
  }

  private static BigDecimal toBigDecimal(Number value) {
    return new BigDecimal(value.toString());
  }

}
