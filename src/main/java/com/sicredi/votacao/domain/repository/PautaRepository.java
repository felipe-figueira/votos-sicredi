package com.sicredi.votacao.domain.repository;

import com.sicredi.votacao.domain.model.Pauta;
import reactor.core.publisher.Mono;

public interface PautaRepository {

    Mono<Pauta> findByDescricao(String descricao);

    Mono<Pauta> save(Pauta pauta);
}
