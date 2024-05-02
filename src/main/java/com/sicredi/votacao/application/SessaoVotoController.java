package com.sicredi.votacao.application;

import com.sicredi.votacao.domain.dto.SessaoVotoDto;
import com.sicredi.votacao.domain.service.SessaoVotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sessao-voto")
public class SessaoVotoController {

    private final SessaoVotoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SessaoVotoDto> abrirSessao(@RequestParam String descricaoPauta,
                                           @RequestParam(value = "tempoEmMinutos", defaultValue = "1") Long tempoEmMinutos) {

        return service.salvarSessaoVoto(tempoEmMinutos, descricaoPauta);
    }
}
