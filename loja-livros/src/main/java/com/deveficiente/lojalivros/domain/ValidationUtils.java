package com.deveficiente.lojalivros.domain;

import static org.apache.commons.lang3.StringUtils.length;

public class ValidationUtils {

  public static boolean isLengthGreaterThan(String value, int maxLength) {
    return length(value) > maxLength;
  }

  public static boolean isLengthLessThan(String value, int minLength) {
    return length(value) < minLength;
  }

}
