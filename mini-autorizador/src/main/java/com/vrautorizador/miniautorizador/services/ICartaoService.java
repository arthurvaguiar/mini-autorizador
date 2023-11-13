package com.vrautorizador.miniautorizador.services;

import com.vrautorizador.miniautorizador.models.Cartao;
import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
public interface ICartaoService {

    ResponseEntity<?> criarOuRetornarExistente(CartaoRequestDto cartaoRequest);

    ResponseEntity<?> obterSaldoDoCartao(String numeroCartao);

    Optional<Cartao> findByNumeroCartao(String numeroCartao);

    Optional<Cartao> mapperDtoToEntity(CartaoRequestDto cartaoRequest);
}