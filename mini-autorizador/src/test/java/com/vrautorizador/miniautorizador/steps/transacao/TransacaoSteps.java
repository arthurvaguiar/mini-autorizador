package com.vrautorizador.miniautorizador.steps.transacao;

import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.services.ICartaoService;
import com.vrautorizador.miniautorizador.services.impl.TransacaoService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.mockito.Mockito;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public class TransacaoSteps {

    private TransacaoService transacaoService = new TransacaoService();
    private ICartaoService cartaoServiceMock = Mockito.mock(ICartaoService.class);
    private double valorTransacao;
    private CartaoRequestDto cartaoRequestDto = new CartaoRequestDto();

    private String resultadoTransacao;

    @Dado("que possuo um cartão {string}, {string}, {double}")
    public void givenUmCartaoValido(String numeroCartao, String senha, double valor) {
        cartaoRequestDto.setNumeroCartao(numeroCartao);
        cartaoRequestDto.setSenha(senha);
        cartaoRequestDto.setValor(valor);
        valorTransacao = valor;

        // Inicializando a instância de TransacaoService com o mock de ICartaoService
        transacaoService = new TransacaoService(cartaoServiceMock);
    }

    @Quando("eu realizo uma transação no valor de {double}")
    public void whenEuRealizoUmaTransacao(double valor) {
        resultadoTransacao = String.valueOf(transacaoService.realizarTransacao(cartaoRequestDto));
    }

    @Então("a transação é processada")
    public void thenATransacaoEProcessadaComSucesso() {
        System.out.println("Resultado da transação: " + resultadoTransacao);
    }
}
