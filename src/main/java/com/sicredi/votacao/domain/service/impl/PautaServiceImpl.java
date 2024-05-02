package com.sicredi.votacao.domain.service.impl;

import com.sicredi.votacao.domain.dto.PautaDto;
import com.sicredi.votacao.domain.dto.ResultadoPautaDto;
import com.sicredi.votacao.domain.exception.*;
import com.sicredi.votacao.domain.mapper.PautaMapper;
import com.sicredi.votacao.domain.model.Pauta;
import com.sicredi.votacao.domain.model.Voto;
import com.sicredi.votacao.domain.repository.PautaRepository;
import com.sicredi.votacao.domain.service.AssociadoService;
import com.sicredi.votacao.domain.service.PautaService;
import com.sicredi.votacao.domain.service.SessaoVotoService;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.sicredi.votacao.domain.constants.ErroConstantes.*;
import static com.sicredi.votacao.domain.utils.ConvertUtils.digitosCpf;
import static java.lang.String.format;
import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    private static final String VOTO_NAO = "NÃO";
    private static final String VOTO_SIM = "SIM";

    private final PautaMapper mapper;
    private final PautaRepository repository;
    private final SessaoVotoService sessaoVotoService;
    private final AssociadoService associadoService;

    @Override
    public Mono<PautaDto> salvarPauta(@NonNull PautaDto pautaDto) {

        return repository.findByDescricao(pautaDto.getDescricao())
                .flatMap(pauta -> Mono.error(new BusinessException(format(PAUTA_JA_EXISTE, pautaDto.getDescricao()), HttpStatus.CONFLICT.value())))
                .switchIfEmpty(repository.save(mapper.toPauta(pautaDto)))
                .map(pauta -> pautaDto);
    }

    @Override
    public Mono<Pauta> salvarVotoNaPauta(@NonNull PautaDto pautaDto) {

        String cpfFormatado = digitosCpf(pautaDto.getVoto().getCpf());

        return associadoService.buscarAssociado(cpfFormatado)
                .switchIfEmpty(Mono.error(new BusinessException(format(ASSOCIADO_NAO_EXISTE, cpfFormatado), HttpStatus.NOT_FOUND.value())))
                .flatMap(associado -> validarSeExitePauta(pautaDto.getDescricao()))
                .flatMap(pauta -> verificaVotoJaRegistrado(pauta, cpfFormatado).thenReturn(pauta))
                .flatMap(pauta -> sessaoVotoService.consultarSessao(pautaDto.getDescricao())
                        .switchIfEmpty(Mono.error(new BusinessException(format(SESSAO_VOTACAO_PARA_PAUTA_NAO_EXISTE, pauta.getDescricao()), HttpStatus.NOT_FOUND.value())))
                        .flatMap(sessaoVotoService::validarSessaoJaFechada)
                        .thenReturn(pauta))
                .flatMap(pauta -> salvarVotoNaPauta(pautaDto, pauta));
    }

    @Override
    public Mono<ResultadoPautaDto> calcularResultadoPauta(@NotBlank String descricaoPauta) {

        return validarSeExitePauta(descricaoPauta)
                .flatMap(pauta -> sessaoVotoService.validarSessaoAindaAberta(descricaoPauta).thenReturn(pauta))
                .flatMap(pauta -> Mono.just(verificarResultadoPauta(pauta)));
    }

    @NonNull
    private Mono<Pauta> salvarVotoNaPauta(@NonNull PautaDto pautaDto,
                                          @NonNull Pauta pauta) {

        List<Voto> votos = Objects.requireNonNullElse(pauta.getVotos(), new ArrayList<>());

        votos.add(Voto.builder()
                .votoAssociado(pautaDto.getVoto().getVoto())
                .cpf(pautaDto.getVoto().getCpf())
                .build());

        return repository.save(pauta.withVotos(votos));
    }


    @Override
    @NonNull
    public Mono<Pauta> validarSeExitePauta(@NotBlank String descricaoPauta) {

        return repository.findByDescricao(descricaoPauta)
                .switchIfEmpty(Mono.error(new BusinessException(format(PAUTA_NAO_EXISTE, descricaoPauta), HttpStatus.NOT_FOUND.value())));
    }


    private Mono<Void> verificaVotoJaRegistrado(@NonNull Pauta pauta,
                                                @NotBlank String cpf) {

        if(emptyIfNull(pauta.getVotos()).stream().anyMatch(voto -> voto.getCpf().equals(cpf))){
            return Mono.error(new BusinessException(format(ASSOCIADO_JA_VOTOU, cpf), HttpStatus.CONFLICT.value()));
        }

        return Mono.empty();
    }

    @NonNull
    private ResultadoPautaDto verificarResultadoPauta(@NonNull Pauta pauta) {

        Map<String, Long> counts = emptyIfNull(pauta.getVotos()).stream()
                .map(voto -> voto.getVotoAssociado().toUpperCase())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long votosSim = counts.getOrDefault(VOTO_SIM, 0L);
        long votosNao = counts.getOrDefault(VOTO_NAO, 0L);

        return ResultadoPautaDto.builder()
                .votosAprovado(votosSim)
                .votosReprovado(votosNao)
                .aprovado(votosSim > votosNao)
                .build();
    }

}
