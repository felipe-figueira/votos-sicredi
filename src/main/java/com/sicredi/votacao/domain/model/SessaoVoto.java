package com.sicredi.votacao.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@With
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class SessaoVoto {

    @Id
    private String id;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFIm;

    private String pauta;

}
