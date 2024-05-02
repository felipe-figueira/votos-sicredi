package com.sicredi.votacao.domain.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class ResultadoPautaDto {

    private Long votosAprovado;
    private Long votosReprovado;
    private Boolean aprovado;
}
