package com.vrautorizador.miniautorizador.exceptions;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public class CartaoInexistenteException  extends RuntimeException{
    public CartaoInexistenteException(String message) {
        super(message);
    }
}
