package com.vrautorizador.miniautorizador.services.factory.validation;

import com.vrautorizador.miniautorizador.repositories.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public class CartaoValidoValidator implements ConstraintValidator<CartaoValido, String> {

    private final CartaoRepository repository;

    @Autowired
    public CartaoValidoValidator(CartaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(CartaoValido constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String numeroCartao, ConstraintValidatorContext context) {
        return validaNumeroCartao(numeroCartao) && !repository.existsByNumeroCartao(numeroCartao);
    }

    private boolean validaNumeroCartao(String numeroCartao) {
        return numeroCartao != null && numeroCartao.trim().length() > 0;
    }
}
