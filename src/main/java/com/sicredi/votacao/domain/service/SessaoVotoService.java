package com.sicredi.votacao.domain.service;

import com.sicredi.votacao.domain.dto.SessaoVotoDto;
import com.sicredi.votacao.domain.model.SessaoVoto;
import jakarta.validation.constraints.NotBlank;
import reactor.core.publisher.Mono;

public interface SessaoVotoService {

    Mono<SessaoVotoDto> salvarSessaoVoto(Long tempoEmMinutos, String pauta);

    Mono<Void>  validarSessaoAindaAberta(String descricaoPauta);

    Mono<Void>  validarSessaoJaFechada(SessaoVoto sessaoVoto);

    Mono<Void> validarSeExiteSessaoParaEssaPauta(String descricaoPauta);

    Mono<SessaoVoto> consultarSessao(String descricaoPauta);
}
