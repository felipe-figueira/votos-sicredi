package com.sicredi.votacao.domain.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Getter
@Builder
@Jacksonized
public class SessaoVotoDto {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String question;
}
