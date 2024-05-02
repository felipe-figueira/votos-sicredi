package com.sicredi.votacao.infrastructure.repository;

import com.sicredi.votacao.domain.model.Pauta;
import com.sicredi.votacao.domain.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class PautaMongoDbRepository implements PautaRepository {

    private final PautaReactiveSpringDataRepository pautaReactiveSpringDataRepository;

    @Override
    public Mono<Pauta> findByDescricao(String descricao) {
        return pautaReactiveSpringDataRepository.findByDescricao(descricao);
    }

    @Override
    public Mono<Pauta> save(Pauta pauta) {
        return pautaReactiveSpringDataRepository.save(pauta);
    }
}
