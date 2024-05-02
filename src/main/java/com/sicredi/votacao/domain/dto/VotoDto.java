package com.sicredi.votacao.domain.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class VotoDto {

    private String cpf;
    private String voto;
}
