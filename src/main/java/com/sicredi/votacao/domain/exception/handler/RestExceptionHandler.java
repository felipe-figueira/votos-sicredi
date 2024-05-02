package com.sicredi.votacao.domain.exception.handler;

import com.sicredi.votacao.domain.exception.BusinessException;
import com.sicredi.votacao.domain.exception.CpfInvalidoException;
import com.sicredi.votacao.domain.exception.ErroResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleCustomException(BusinessException exception) {

        log.error(exception.getMessage());
        return ResponseEntity.status(exception.getCode()).body(ErroResponse.builder().message(exception.getMessage()).build());
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public final ResponseEntity<Object> handleCustomException(CpfInvalidoException exception) {

        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErroResponse.builder().message(exception.getMessage()).build());
    }
}
