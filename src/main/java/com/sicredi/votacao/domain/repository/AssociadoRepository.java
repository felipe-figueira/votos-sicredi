package com.sicredi.votacao.domain.repository;

import com.sicredi.votacao.domain.model.Associado;
import reactor.core.publisher.Mono;

public interface AssociadoRepository {

    Mono<Associado> findByCpf(String cpf);

    Mono<Associado> save(Associado associado);
}
