package com.sicredi.votacao.domain.mapper;

import com.sicredi.votacao.domain.dto.PautaDto;
import com.sicredi.votacao.domain.model.Pauta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    Pauta toPauta(PautaDto pautaDto);
}
