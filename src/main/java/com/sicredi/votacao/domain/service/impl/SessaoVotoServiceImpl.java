package com.sicredi.votacao.domain.service.impl;

import com.sicredi.votacao.domain.dto.SessaoVotoDto;
import com.sicredi.votacao.domain.exception.BusinessException;
import com.sicredi.votacao.domain.mapper.SessaoVotoMapper;
import com.sicredi.votacao.domain.model.SessaoVoto;
import com.sicredi.votacao.domain.repository.PautaRepository;
import com.sicredi.votacao.domain.repository.SessaoVotoRepository;
import com.sicredi.votacao.domain.service.SessaoVotoService;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static com.sicredi.votacao.domain.constants.ErroConstantes.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SessaoVotoServiceImpl implements SessaoVotoService {

    private final SessaoVotoRepository repository;
    private final SessaoVotoMapper mapper;
    private final PautaRepository pautaRepository;

    @NonNull
    @Override
    public Mono<SessaoVotoDto> salvarSessaoVoto(@NonNull Long tempoEmMinutos,
                                                @NonNull String descricaoPauta) {

        return pautaRepository.findByDescricao(descricaoPauta)
                .switchIfEmpty(Mono.error(new BusinessException(format(PAUTA_NAO_EXISTE, descricaoPauta), HttpStatus.NOT_FOUND.value())))
                .flatMap(pauta -> validarSeExiteSessaoParaEssaPauta(pauta.getDescricao()))
                .flatMap(pauta -> repository.save(sessaoVotoBuild(tempoEmMinutos, descricaoPauta)))
                .map(mapper::toSessaoVotoDto);
    }

    @NonNull
    @Override
    public Mono<Void> validarSeExiteSessaoParaEssaPauta(@NotBlank String descricaoPauta) {

        return repository.findByPauta(descricaoPauta)
                .flatMap(votacao -> Mono.error(new BusinessException(format(SESSAO_VOTACAO_JA_EXISTE, votacao.getPauta()), HttpStatus.NOT_FOUND.value())));
    }

    @NonNull
    @Override
    public Mono<SessaoVoto> consultarSessao(@NotBlank String descricaoPauta) {

        return repository.findByPauta(descricaoPauta);
    }

    @Override
    public Mono<Void> validarSessaoAindaAberta(@NonNull String descricaoPauta) {

        return repository.findByPauta(descricaoPauta)
                .switchIfEmpty(Mono.error(new BusinessException(format(SESSAO_VOTACAO_PARA_PAUTA_NAO_EXISTE, descricaoPauta), HttpStatus.NOT_FOUND.value())))
                .flatMap(sessaoVoto -> {
                    if (LocalDateTime.now().isBefore(sessaoVoto.getDataFIm())) {
                        return Mono.error(new BusinessException(format(SESSAO_VOTACAO_AINDA_ABERTA, sessaoVoto.getPauta()), HttpStatus.FORBIDDEN.value()));
                    }
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Void> validarSessaoJaFechada(@NonNull SessaoVoto sessaoVoto) {

        if (LocalDateTime.now().isAfter(sessaoVoto.getDataFIm())) {
            return Mono.error(new BusinessException(String.format(SESSAO_VOTACAO_JA_ENCERRADA, sessaoVoto.getPauta()), HttpStatus.FORBIDDEN.value()));
        }

        return Mono.empty();
    }

    @NonNull
    private SessaoVoto sessaoVotoBuild(@NonNull Long tempoEmMinutos,
                                       @NotBlank String descricaoPauta) {

        return SessaoVoto.builder()
                .dataInicio(LocalDateTime.now())
                .dataFIm(LocalDateTime.now().plusMinutes(tempoEmMinutos))
                .pauta(descricaoPauta)
                .build();
    }

}
