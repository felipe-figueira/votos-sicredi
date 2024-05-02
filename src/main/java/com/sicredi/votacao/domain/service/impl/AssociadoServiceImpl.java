package com.sicredi.votacao.domain.service.impl;

import com.sicredi.votacao.domain.dto.AssociadoDto;
import com.sicredi.votacao.domain.exception.BusinessException;
import com.sicredi.votacao.domain.mapper.AssociadoMapper;
import com.sicredi.votacao.domain.model.Associado;
import com.sicredi.votacao.domain.repository.AssociadoRepository;
import com.sicredi.votacao.domain.service.AssociadoService;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.sicredi.votacao.domain.constants.ErroConstantes.ASSOCIADO_JA_EXISTE;
import static com.sicredi.votacao.domain.utils.ConvertUtils.digitosCpf;
import static java.lang.String.format;

@RequiredArgsConstructor
@Service
@Slf4j
public class AssociadoServiceImpl implements AssociadoService {

    private final AssociadoRepository repository;
    private final AssociadoMapper associadoMapper;

    @Override
    @NonNull
    public Mono<AssociadoDto> salvarAssociado(@NonNull AssociadoDto associadoDto) {

        return buscarAssociado(digitosCpf(associadoDto.getCpf()))
                .flatMap(associadoExistente -> Mono.error(new BusinessException(format(ASSOCIADO_JA_EXISTE, associadoDto.getCpf()), HttpStatus.CONFLICT.value())))
                .switchIfEmpty(repository.save(associadoMapper.toAssociado(associadoDto).withCpf(digitosCpf(associadoDto.getCpf())))).thenReturn(associadoDto);
    }
    @Override
    @NonNull
    public Mono<Associado> buscarAssociado(@NotBlank String cpf) {

        return repository.findByCpf(cpf);
    }

}
