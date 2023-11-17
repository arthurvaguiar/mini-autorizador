package com.vrautorizador.miniautorizador.exceptions;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public class CartaoInvalidoException extends RuntimeException{
    public CartaoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
