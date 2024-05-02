package com.sicredi.votacao.domain.exception;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class ErroResponse {

    private String message;

}
