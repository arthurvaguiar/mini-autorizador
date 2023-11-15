package com.vrautorizador.miniautorizador.exceptions;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public class CartaoInvalidoException extends Exception{
    public CartaoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
