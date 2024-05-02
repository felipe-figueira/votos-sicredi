package com.sicredi.votacao.domain.service;

import com.sicredi.votacao.domain.dto.PautaDto;
import com.sicredi.votacao.domain.dto.ResultadoPautaDto;
import com.sicredi.votacao.domain.model.Pauta;
import reactor.core.publisher.Mono;

public interface PautaService {

    Mono<PautaDto> salvarPauta(PautaDto pautaDto);

    Mono<Pauta> salvarVotoNaPauta(PautaDto pautaDto);

    Mono<ResultadoPautaDto> calcularResultadoPauta(String question);

    Mono<Pauta> validarSeExitePauta(String description);
}
