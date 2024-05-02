package com.sicredi.votacao.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@With
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Pauta {

    @Id
    private String id;

    private String descricao;

    private List<Voto> votos;
}
