package com.sicredi.votacao.domain.mapper;

import com.sicredi.votacao.domain.dto.AssociadoDto;
import com.sicredi.votacao.domain.model.Associado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssociadoMapper {

    Associado toAssociado(AssociadoDto associadoDto);
}
