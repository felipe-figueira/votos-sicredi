package com.sicredi.votacao.infrastructure.repository;

import com.sicredi.votacao.domain.model.Associado;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AssociadoReactiveSpringDataRepository extends ReactiveMongoRepository<Associado, String> {

    Mono<Associado> findByCpf(String cpf);
}
