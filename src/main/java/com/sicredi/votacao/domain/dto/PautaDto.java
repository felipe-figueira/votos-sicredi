package com.sicredi.votacao.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class PautaDto {

    @NotBlank
    private String descricao;

    private VotoDto voto;
}
