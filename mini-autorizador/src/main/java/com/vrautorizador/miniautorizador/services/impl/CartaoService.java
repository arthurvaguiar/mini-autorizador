package com.vrautorizador.miniautorizador.services.impl;

import com.vrautorizador.miniautorizador.exceptions.CartaoInvalidoException;
import com.vrautorizador.miniautorizador.models.Cartao;
import com.vrautorizador.miniautorizador.models.dto.CartaoDto;
import com.vrautorizador.miniautorizador.repositories.CartaoRepository;
import com.vrautorizador.miniautorizador.services.ICartaoService;
import com.vrautorizador.miniautorizador.services.factory.CartaoFactory;
import com.vrautorizador.miniautorizador.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@Service
@Validated
public class CartaoService implements ICartaoService {

    private final CartaoFactory factory;
    private final CartaoRepository repository;

    @Autowired
    public CartaoService(CartaoFactory factory, CartaoRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Object> criarOuRetornarExistente(CartaoDto cartaoRequest) {
        Cartao novoCartao = factory.criarCartao(cartaoRequest.getNumeroCartao(), SecurityUtils.hashPassword(cartaoRequest.getSenha()));
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(novoCartao));
    }

    @Override
    public ResponseEntity<Double> obterSaldoDoCartao(String numeroCartao) {
        return numeroCartao.isEmpty() ?
                ResponseEntity.noContent().build() :
                this.findByNumeroCartao(numeroCartao)
                        .map(cartao -> ResponseEntity.status(HttpStatus.OK).body(cartao.getValor()))
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public Optional<Cartao> findByNumeroCartao(String numeroCartao) {
        return Optional.ofNullable(repository.findByNumeroCartao(numeroCartao));
    }

}
