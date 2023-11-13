package com.vrautorizador.miniautorizador.services.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

@Service
public class ValidacaoService {

    private final Validator validator;

    @Autowired
    public ValidacaoService(Validator validator) {
        this.validator = validator;
    }
    public String validarNumeroCartao(String valor) {
        var violations = validator.validate(valor);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return null;
    }
}
