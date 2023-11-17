package com.vrautorizador.miniautorizador.services.impl;

import com.vrautorizador.miniautorizador.exceptions.CartaoInvalidoException;
import com.vrautorizador.miniautorizador.models.Cartao;
import com.vrautorizador.miniautorizador.models.Transacao;
import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.repositories.TransacaoRepository;
import com.vrautorizador.miniautorizador.services.ICartaoService;
import com.vrautorizador.miniautorizador.services.ITransacaoService;
import com.vrautorizador.miniautorizador.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@Service
public class TransacaoService implements ITransacaoService {

    private TransacaoRepository repository;

    private final ICartaoService cartaoService;

    @Autowired
    public TransacaoService(CartaoService cartaoService, TransacaoRepository repository) {
        this.cartaoService = cartaoService;
        this.repository = repository;
    }

    @Override
    public void realizarTransacao(CartaoRequestDto cartaoDto) {
        Optional<Cartao> cartao = Optional.ofNullable(cartaoService.findByNumeroCartao(cartaoDto.getNumeroCartao())
                .orElseThrow(() -> new CartaoInvalidoException("CARTAO_INVALIDO")));

        cartao.get().isCardValid(cartao.get().getNumeroCartao(), cartaoDto.getNumeroCartao());

        SecurityUtils.isPasswordValid(cartao.get().getSenha(), cartaoDto.getSenha());

        salvarEProcessarTransacao(cartao.get(), cartaoDto.getValor());
    }

    private void salvarEProcessarTransacao(Cartao cartao, double valor) {
        cartao.debitarSaldo(valor);
        Transacao transacao = new Transacao(cartao, cartao.getSenha(), valor);
        repository.save(transacao);
    }


}
