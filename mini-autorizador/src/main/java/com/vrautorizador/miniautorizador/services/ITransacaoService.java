package com.vrautorizador.miniautorizador.services;

import com.vrautorizador.miniautorizador.exceptions.CartaoInvalidoException;
import com.vrautorizador.miniautorizador.exceptions.SaldoInfucienteException;
import com.vrautorizador.miniautorizador.exceptions.SenhaInvalidaException;
import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public interface ITransacaoService {
    void realizarTransacao(CartaoRequestDto cartaoRequest) throws SenhaInvalidaException, SaldoInfucienteException, CartaoInvalidoException;
}
