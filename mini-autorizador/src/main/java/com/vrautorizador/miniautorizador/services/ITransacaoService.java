package com.vrautorizador.miniautorizador.services;

import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import org.springframework.http.ResponseEntity;

public interface ITransacaoService {
    ResponseEntity<?> realizarTransacao(CartaoRequestDto cartaoRequest);
}
