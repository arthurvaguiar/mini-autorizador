package com.vrautorizador.miniautorizador.exceptions;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
