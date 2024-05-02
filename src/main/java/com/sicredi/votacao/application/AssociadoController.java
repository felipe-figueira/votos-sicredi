package com.sicredi.votacao.application;

import com.sicredi.votacao.domain.dto.AssociadoDto;
import com.sicredi.votacao.domain.service.AssociadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/associado")
public class AssociadoController {

    private final AssociadoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<AssociadoDto> salvarAssociado(@Valid @RequestBody AssociadoDto associadoDto) {

        return service.salvarAssociado(associadoDto);
    }

}
