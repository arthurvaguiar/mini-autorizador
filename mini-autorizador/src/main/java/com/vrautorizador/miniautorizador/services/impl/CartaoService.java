package com.vrautorizador.miniautorizador.services.impl;

import com.vrautorizador.miniautorizador.models.Cartao;
import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.repositories.CartaoRepository;
import com.vrautorizador.miniautorizador.services.ICartaoService;
import com.vrautorizador.miniautorizador.services.factory.CartaoFactory;
import com.vrautorizador.miniautorizador.services.factory.ValidacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CartaoService implements ICartaoService {

    private final CartaoFactory factory;
    private final CartaoRepository repository;
    private final ValidacaoService validacaoService;


    @Autowired
    public CartaoService(CartaoFactory factory, CartaoRepository repository, ValidacaoService validacaoService) {
        this.factory = factory;
        this.repository = repository;
        this.validacaoService = validacaoService;
    }

    @Override
    public ResponseEntity<?> criarOuRetornarExistente(CartaoRequestDto cartaoRequest) {
        Cartao novoCartao = factory.criarCartao(cartaoRequest.getNumeroCartao(), cartaoRequest.getSenha());
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(novoCartao));
    }

}
