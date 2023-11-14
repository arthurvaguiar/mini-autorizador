package com.vrautorizador.miniautorizador.services.impl;

import com.vrautorizador.miniautorizador.exceptions.CartaoInexistenteException;
import com.vrautorizador.miniautorizador.exceptions.CartaoInvalidoException;
import com.vrautorizador.miniautorizador.exceptions.SaldoInfucienteException;
import com.vrautorizador.miniautorizador.exceptions.SenhaInvalidaException;
import com.vrautorizador.miniautorizador.models.Cartao;
import com.vrautorizador.miniautorizador.models.Transacao;
import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.repositories.TransacaoRepository;
import com.vrautorizador.miniautorizador.services.ICartaoService;
import com.vrautorizador.miniautorizador.services.ITransacaoService;
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
    public void realizarTransacao(CartaoRequestDto cartaoDto) throws SenhaInvalidaException, SaldoInfucienteException, CartaoInvalidoException, CartaoInexistenteException {
        Optional<Cartao> cartao = cartaoService.findByNumeroCartao(cartaoDto.getNumeroCartao());
        try {
            cartao.orElseThrow(() -> new CartaoInexistenteException("CARTAO_INEXISTENTE"));

            cartaoService.cartaoValido(cartao.get().getNumeroCartao(), cartaoDto.getNumeroCartao());

            cartaoService.validarSenha(cartao.get().getSenha(), cartaoDto.getSenha());

            salvarEProcessarTransacao(cartao.get(), cartaoDto.getValor());

        } catch (SenhaInvalidaException e) {
            throw new SenhaInvalidaException("SENHA_INVALIDA");
        } catch (SaldoInfucienteException e) {
            throw new SaldoInfucienteException("SALDO_INSUFICIENTE");
        } catch (CartaoInvalidoException e) {
            throw new CartaoInvalidoException("CARTAO_INVALIDO");
        }

    }


    private void salvarEProcessarTransacao(Cartao cartao, double valor) throws SaldoInfucienteException {
        cartao.debitarSaldo(valor);
        Transacao transacao = this.mapper(cartao, valor);
        repository.save(transacao);
    }

    private Transacao mapper(Cartao cartao, double valor) {
        return new Transacao(cartao, cartao.getSenha(), valor);
    }


}
