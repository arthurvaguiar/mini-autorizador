package com.vrautorizador.miniautorizador.exceptions;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public class CartaoInexistenteException extends Exception{
    public CartaoInexistenteException(String mensagem) {
        super(mensagem);
    }
}
