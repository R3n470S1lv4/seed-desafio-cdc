package com.deveficiente.lojalivros.controller.exception.hadler;

import com.deveficiente.lojalivros.domain.DomainException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandle extends ResponseEntityExceptionHandler {

  //TODO UM SIMPLES TRATADOR DE DomainException PARA QUE SEJA POSSIVEL CAPTURAR A MENSAGEM NO RETORNO DA CHAMDA DA API, DEVE EVOLUIR PARA ALGO MAIS ROBUTO
  @ExceptionHandler(DomainException.class)
  public ResponseEntity<ErrorDTO> domainException(DomainException domainException,
      WebRequest webRequest) {

    String message = domainException.getMessage();

    return ResponseEntity.badRequest().body(new ErrorDTO(message));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDTO> xxx(DomainException domainException,
      WebRequest webRequest) {
    return ResponseEntity.badRequest().body(new ErrorDTO("XXX"));
  }


}
