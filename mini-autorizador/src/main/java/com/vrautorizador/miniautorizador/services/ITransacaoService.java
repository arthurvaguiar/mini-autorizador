package com.vrautorizador.miniautorizador.services;

import com.vrautorizador.miniautorizador.models.Cartao;
import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import org.springframework.http.ResponseEntity;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public interface ITransacaoService {
    ResponseEntity<Cartao> realizarTransacao(CartaoRequestDto cartaoRequest);
}
