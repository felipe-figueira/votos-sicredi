package com.sicredi.votacao.domain.repository;

import com.sicredi.votacao.domain.model.SessaoVoto;
import reactor.core.publisher.Mono;

public interface SessaoVotoRepository {

    Mono<SessaoVoto> findByPauta(String description);

    Mono<SessaoVoto> save(SessaoVoto sessaoVoto);
}
