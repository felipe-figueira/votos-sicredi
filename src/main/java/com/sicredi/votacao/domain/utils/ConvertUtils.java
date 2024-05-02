package com.sicredi.votacao.domain.utils;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class ConvertUtils {
    private ConvertUtils() {
    }

    @NonNull
    public static String digitosCpf(@NotBlank String cpf){
        return cpf.replaceAll("\\D", EMPTY);
    }
}
