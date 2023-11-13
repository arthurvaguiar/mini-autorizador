package com.vrautorizador.miniautorizador.services.impl;

import com.vrautorizador.miniautorizador.models.Cartao;
import com.vrautorizador.miniautorizador.models.Transacao;
import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.repositories.TransacaoRepository;
import com.vrautorizador.miniautorizador.services.ICartaoService;
import com.vrautorizador.miniautorizador.services.ITransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransacaoService implements ITransacaoService {

    private final TransacaoRepository repository;

    private final ICartaoService cartaoService;

    @Autowired
    public TransacaoService(TransacaoRepository repository, ICartaoService cartaoService) {
        this.repository = repository;
        this.cartaoService = cartaoService;
    }

    @Override
    public ResponseEntity<?> realizarTransacao(CartaoRequestDto cartaoDto) {
        Optional<Cartao> cartaoRequest = cartaoService.mapperDtoToEntity(cartaoDto);

        return cartaoService.findByNumeroCartao(cartaoRequest.get().getNumeroCartao())
                .map(cartao -> cartao.getSenha().equals(cartaoRequest.get().getSenha()) ?
                        (cartao.getValor() >= cartaoRequest.get().getValor() ?
                                salvarEProcessarTransacao(cartao, cartaoRequest.get().getValor()) :
                                ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("SALDO_INSUFICIENTE")) :
                        ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("SENHA_INVALIDA"))
                .orElse(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("CARTAO_INEXISTENTE"));
    }

    private ResponseEntity<?> salvarEProcessarTransacao(Cartao cartao, double valor) {
        cartao.setValor(cartao.getValor() - valor);
        Transacao transacao = this.mapper(cartao);
        repository.save(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).body("Transação realizada com sucesso");
    }

    private Transacao mapper(Cartao cartao) {
        return new Transacao(cartao, cartao.getSenha(), cartao.getValor());
    }
}
