package com.vrautorizador.miniautorizador.steps.transacao;

import com.vrautorizador.miniautorizador.exceptions.CartaoInexistenteException;
import com.vrautorizador.miniautorizador.exceptions.CartaoInvalidoException;
import com.vrautorizador.miniautorizador.exceptions.SaldoInfucienteException;
import com.vrautorizador.miniautorizador.exceptions.SenhaInvalidaException;
import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.services.impl.TransacaoService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public class TransacaoSteps {

    @Autowired
    private TransacaoService transacaoService;
    private CartaoRequestDto cartaoRequestDto = new CartaoRequestDto();

    @Dado("que possuo um cartão {string}, {string}, {double}")
    public void givenUmCartaoValido(String numeroCartao, String senha, double valor) {
        cartaoRequestDto.setNumeroCartao(numeroCartao);
        cartaoRequestDto.setSenha(senha);
        cartaoRequestDto.setValor(valor);

    }

    @Então("eu realizo uma transação")
    public void whenEuRealizoUmaTransacao() throws SenhaInvalidaException, SaldoInfucienteException, CartaoInexistenteException, CartaoInvalidoException {
        transacaoService.realizarTransacao(cartaoRequestDto);
    }

}
