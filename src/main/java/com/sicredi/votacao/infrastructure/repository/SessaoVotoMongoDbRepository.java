package com.sicredi.votacao.infrastructure.repository;

import com.sicredi.votacao.domain.model.SessaoVoto;
import com.sicredi.votacao.domain.repository.SessaoVotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class SessaoVotoMongoDbRepository implements SessaoVotoRepository {

    private final SessaoVotoReactiveSpringDataRepository sessaoVotoReactiveSpringDataRepository;

    @Override
    public Mono<SessaoVoto> findByPauta(String descricaoPauta) {
        return sessaoVotoReactiveSpringDataRepository.findByPauta(descricaoPauta);
    }

    @Override
    public Mono<SessaoVoto> save(SessaoVoto sessaoVoto) {
        return sessaoVotoReactiveSpringDataRepository.save(sessaoVoto);
    }
}
