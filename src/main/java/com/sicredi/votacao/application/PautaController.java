package com.sicredi.votacao.application;

import com.sicredi.votacao.domain.dto.PautaDto;
import com.sicredi.votacao.domain.dto.ResultadoPautaDto;
import com.sicredi.votacao.domain.model.Pauta;
import com.sicredi.votacao.domain.service.PautaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pauta")
public class PautaController {

    private final PautaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PautaDto> salvarPauta(@Valid @RequestBody PautaDto pautaDto) {

        return service.salvarPauta(pautaDto);
    }

    @PutMapping("/voto")
    public Mono<Pauta> computarVotoNaPauta(@Valid @RequestBody PautaDto pautaDto) {

        return service.salvarVotoNaPauta(pautaDto);
    }

    @GetMapping("/resultado")
    public Mono<ResultadoPautaDto> calcularResultadoDaPauta(@RequestParam String descricaoPauta) {

        return service.calcularResultadoPauta(descricaoPauta);
    }

}
