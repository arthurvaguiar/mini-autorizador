package com.vrautorizador.miniautorizador.services;

import com.vrautorizador.miniautorizador.models.Cartao;
import com.vrautorizador.miniautorizador.models.dto.CartaoDto;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@Validated
public interface ICartaoService {

    Cartao criarOuRetornarExistente(CartaoDto cartaoRequest);

    Double obterSaldoDoCartao(String numeroCartao);

    Optional<Cartao> findByNumeroCartao(String numeroCartao);
}
