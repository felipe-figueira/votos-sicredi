package com.sicredi.votacao.domain.anotations;

import com.sicredi.votacao.domain.exception.CpfInvalidoException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotBlank;

import java.util.Arrays;
import java.util.HashSet;

import static com.sicredi.votacao.domain.constants.ErroConstantes.CPF_INVALIDO;
import static java.lang.String.format;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

public class CpfValidator implements ConstraintValidator<ValidCPF, String> {

    @Override
    public void initialize(ValidCPF constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(@NotBlank String cpf, ConstraintValidatorContext context) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11 || new HashSet<>(Arrays.asList(cpf.split(""))).size() == 1) {
            return false;
        }

        int[] weightsFirstDigit = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weightsSecondDigit = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        int firstDigit = calculateDigit(cpf.substring(0, 9), weightsFirstDigit);
        int secondDigit = calculateDigit(cpf.substring(0, 9) + firstDigit, weightsSecondDigit);

        boolean isvalid = cpf.equals(cpf.substring(0, 9) + firstDigit + secondDigit);

        if(isFalse(isvalid)){
            throw new CpfInvalidoException(format(CPF_INVALIDO, cpf));
        }

        return isvalid;
    }

    private static int calculateDigit(@NotBlank String str, int[] weights) {
        int sum = 0;
        for (int index = 0; index < str.length(); index++) {
            int num = Integer.parseInt(str.substring(index, index + 1));
            sum += num * weights[index];
        }
        int result = 11 - (sum % 11);
        return result > 9 ? 0 : result;
    }
}
