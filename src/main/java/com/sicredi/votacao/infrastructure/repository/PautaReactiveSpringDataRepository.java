package com.sicredi.votacao.infrastructure.repository;

import com.sicredi.votacao.domain.model.Pauta;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PautaReactiveSpringDataRepository extends ReactiveMongoRepository<Pauta, String> {

    Mono<Pauta> findByDescricao(String descricao);
}
