package com.vrautorizador.miniautorizador.services.impl;

import com.vrautorizador.miniautorizador.exceptions.CartaoInexistenteException;
import com.vrautorizador.miniautorizador.exceptions.CartaoInvalidoException;
import com.vrautorizador.miniautorizador.models.Cartao;
import com.vrautorizador.miniautorizador.models.dto.CartaoDto;
import com.vrautorizador.miniautorizador.repositories.CartaoRepository;
import com.vrautorizador.miniautorizador.services.ICartaoService;
import com.vrautorizador.miniautorizador.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final CartaoRepository repository;

    @Autowired
    public CartaoService(CartaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cartao criarOuRetornarExistente(CartaoDto cartaoRequest) {
        if (cartaoRequest.getNumeroCartao().isEmpty()) {
            throw new CartaoInvalidoException("CARTAO_INVALIDO");
        }

        if (repository.existsByNumeroCartao(cartaoRequest.getNumeroCartao())) {
            throw new CartaoInvalidoException("CARTAO_JA_EXISTE");
        }
        var novoCartao = new Cartao(cartaoRequest.getNumeroCartao(), SecurityUtils.hashPassword(cartaoRequest.getSenha()));
        repository.save(novoCartao);
        return novoCartao;
    }

    @Override
    public Double obterSaldoDoCartao(String numeroCartao) {

        if (!repository.existsByNumeroCartao(numeroCartao)) {
            throw new CartaoInexistenteException("CARTAO_INVALIDO");
        }

        var cartao = this.findByNumeroCartao(numeroCartao);

        if (cartao.isEmpty()) {
            throw new CartaoInexistenteException("CARTAO_INVALID");
        }
        return cartao.get().getValor();
    }

    @Override
    public Optional<Cartao> findByNumeroCartao(String numeroCartao) {
        return Optional.ofNullable(repository.findByNumeroCartao(numeroCartao));
    }

}
