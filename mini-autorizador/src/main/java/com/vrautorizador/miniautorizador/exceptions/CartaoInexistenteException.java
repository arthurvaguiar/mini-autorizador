package com.vrautorizador.miniautorizador.exceptions;

public class CartaoInexistenteException extends Exception{
    public CartaoInexistenteException(String mensagem) {
        super(mensagem);
    }
}
