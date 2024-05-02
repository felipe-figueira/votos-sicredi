package com.sicredi.votacao.domain.service;

import com.sicredi.votacao.domain.dto.AssociadoDto;
import com.sicredi.votacao.domain.model.Associado;
import reactor.core.publisher.Mono;

public interface AssociadoService {
    Mono<AssociadoDto> salvarAssociado(AssociadoDto associadoDto);

    Mono<Associado> buscarAssociado(String cpf);
}
