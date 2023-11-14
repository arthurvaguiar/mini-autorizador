package com.vrautorizador.miniautorizador.services;

import com.vrautorizador.miniautorizador.exceptions.CartaoInvalidoException;
import com.vrautorizador.miniautorizador.exceptions.SenhaInvalidaException;
import com.vrautorizador.miniautorizador.models.Cartao;
import com.vrautorizador.miniautorizador.models.dto.CartaoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@Validated
public interface ICartaoService {

    ResponseEntity<Object> criarOuRetornarExistente(CartaoDto cartaoRequest);

    ResponseEntity<Double> obterSaldoDoCartao(String numeroCartao);

    Optional<Cartao> findByNumeroCartao(String numeroCartao);


    void cartaoValido(String numeroCartao, String numeroCartao1) throws CartaoInvalidoException;

    void validarSenha(String senha, String senha1) throws SenhaInvalidaException;
}
