package com.vrautorizador.miniautorizador.services;

import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public interface ITransacaoService {
    void realizarTransacao(CartaoRequestDto cartaoRequest);
}
