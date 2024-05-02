package com.sicredi.votacao.domain.dto;

import com.sicredi.votacao.domain.anotations.ValidCPF;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;

@Getter
@Builder
@Jacksonized
public class AssociadoDto {

    @ValidCPF
    private String cpf;

    @NotBlank
    private String nome;

}
