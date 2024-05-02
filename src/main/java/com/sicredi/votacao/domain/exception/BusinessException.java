package com.sicredi.votacao.domain.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final Integer code;

    public BusinessException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
