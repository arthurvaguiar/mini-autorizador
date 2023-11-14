package com.vrautorizador.miniautorizador.services.factory.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CartaoValidoValidator.class)
public @interface CartaoValido {

    String message() default "Número do cartão inválido ou já existe na base de dados. Verifique o número do cartão.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
