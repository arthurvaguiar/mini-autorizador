package com.vrautorizador.miniautorizador.services.impl;

import com.vrautorizador.miniautorizador.models.Cartao;
import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.repositories.CartaoRepository;
import com.vrautorizador.miniautorizador.services.ICartaoService;
import com.vrautorizador.miniautorizador.services.factory.CartaoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

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
    public ResponseEntity<?> criarOuRetornarExistente(CartaoRequestDto cartaoRequest) {
        Cartao novoCartao = factory.criarCartao(cartaoRequest.getNumeroCartao(), cartaoRequest.getSenha());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(novoCartao));
    }

    @Override
    public ResponseEntity<?> obterSaldoDoCartao(String numeroCartao) {
        return Optional.ofNullable(this.findByNumeroCartao(numeroCartao).get())
                .map(cartao -> {
                    return ResponseEntity.status(HttpStatus.OK).body(cartao.getValor());
                }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public Optional<Cartao> findByNumeroCartao(String numeroCartao) {
        return Optional.ofNullable(repository.findByNumeroCartao(numeroCartao));
    }

    public Optional<Cartao> mapperDtoToEntity(CartaoRequestDto cartaoRequest) {
        return Optional.ofNullable(this.convertToEntity(cartaoRequest));
    }

    private Cartao convertToEntity(CartaoRequestDto cartaoRequest) {
        return new Cartao(cartaoRequest.getNumeroCartao(), cartaoRequest.getSenha(), cartaoRequest.getValor());
    }


}
