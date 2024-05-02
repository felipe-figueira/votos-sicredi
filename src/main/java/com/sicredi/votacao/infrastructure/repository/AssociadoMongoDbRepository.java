package com.sicredi.votacao.infrastructure.repository;

import com.sicredi.votacao.domain.model.Associado;
import com.sicredi.votacao.domain.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class AssociadoMongoDbRepository implements AssociadoRepository {

    private final AssociadoReactiveSpringDataRepository associadoReactiveSpringDataRepository;

    @Override
    public Mono<Associado> findByCpf(String cpf) {
        return associadoReactiveSpringDataRepository.findByCpf(cpf);
    }

    @Override
    public Mono<Associado> save(Associado entity) {
        return associadoReactiveSpringDataRepository.save(entity);
    }
}
