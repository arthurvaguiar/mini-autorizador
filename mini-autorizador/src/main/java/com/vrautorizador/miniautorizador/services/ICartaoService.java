package com.vrautorizador.miniautorizador.services;

import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ICartaoService {

    ResponseEntity<?> criarOuRetornarExistente(CartaoRequestDto cartaoRequest);

}
