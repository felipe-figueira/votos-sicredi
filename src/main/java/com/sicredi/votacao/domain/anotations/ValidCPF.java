package com.sicredi.votacao.domain.anotations;

import com.sicredi.votacao.domain.constants.ErroConstantes;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CpfValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidCPF {

    String message() default ErroConstantes.CPF_INVALIDO;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
