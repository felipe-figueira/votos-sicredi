package com.sicredi.votacao.domain.mapper;

import com.sicredi.votacao.domain.dto.SessaoVotoDto;
import com.sicredi.votacao.domain.model.SessaoVoto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessaoVotoMapper {

    SessaoVotoDto toSessaoVotoDto(SessaoVoto sessaoVoto);
}
