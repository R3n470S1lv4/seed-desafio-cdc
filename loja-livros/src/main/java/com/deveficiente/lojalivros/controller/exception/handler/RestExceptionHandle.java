package com.deveficiente.lojalivros.controller.exception.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.deveficiente.lojalivros.domain.exceptions.PreConditionException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandle {

  @ExceptionHandler(PreConditionException.class)
  public ResponseEntity<ApiErrorDTO> domainException(PreConditionException preConditionException,
      WebRequest webRequest) {

    return ResponseEntity.badRequest()
        .body(new ApiErrorDTO(BAD_REQUEST, preConditionException.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiErrorDTO> validationExceptionHanlde(MethodArgumentNotValidException ex) {
    List<FieldErrorDTO> errors = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(e -> new FieldErrorDTO(e.getField(),
            e.getDefaultMessage()))
        .toList();

    return ResponseEntity.badRequest().body(new ApiErrorDTO(BAD_REQUEST, errors));
  }

}
