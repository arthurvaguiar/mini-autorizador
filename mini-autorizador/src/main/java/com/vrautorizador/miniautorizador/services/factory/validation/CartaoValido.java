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

    String message() default "Numero do cartao invalido ou ja existe na base de dados. Verifique o numero do cartao.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
