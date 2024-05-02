package com.sicredi.votacao.infrastructure.repository;

import com.sicredi.votacao.domain.model.SessaoVoto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SessaoVotoReactiveSpringDataRepository extends ReactiveMongoRepository<SessaoVoto, String> {

    Mono<SessaoVoto> findByPauta(String descricaoPauta);

}
