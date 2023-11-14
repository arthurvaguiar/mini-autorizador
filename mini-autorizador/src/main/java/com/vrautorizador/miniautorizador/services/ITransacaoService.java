package com.vrautorizador.miniautorizador.services;

import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import org.springframework.http.ResponseEntity;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public interface ITransacaoService {
    ResponseEntity<?> realizarTransacao(CartaoRequestDto cartaoRequest);
}
