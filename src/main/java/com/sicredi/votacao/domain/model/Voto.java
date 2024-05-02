package com.sicredi.votacao.domain.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@With
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Voto {

    private String cpf;
    private String votoAssociado;
}
