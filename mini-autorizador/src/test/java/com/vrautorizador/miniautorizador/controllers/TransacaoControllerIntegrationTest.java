package com.vrautorizador.miniautorizador.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrautorizador.miniautorizador.exceptions.CartaoInvalidoException;
import com.vrautorizador.miniautorizador.exceptions.SaldoInfucienteException;
import com.vrautorizador.miniautorizador.exceptions.SenhaInvalidaException;
import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.services.ITransacaoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
class TransacaoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ITransacaoService transacaoService;
    private CartaoRequestDto cartaoRequestDto;

    private final String PATH = "/transacoes";


    @Test
    void testRealizarTransacaoComSucesso() throws Exception {
        cartaoRequestDto = new CartaoRequestDto("6549873025634501", "1234", 10.0);

        String requestBody = objectMapper.writeValueAsString(cartaoRequestDto);

        transacaoService.realizarTransacao(cartaoRequestDto);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Transação realizada com sucesso"));

    }

    @Test
    void testRealizarTransacaoComCartaoInvalidO() throws Exception {
        cartaoRequestDto = new CartaoRequestDto("6549873025634501999", "1234", 10.0);

        String requestBody = objectMapper.writeValueAsString(cartaoRequestDto);

        Exception exception = assertThrows(CartaoInvalidoException.class, () -> {
            transacaoService.realizarTransacao(cartaoRequestDto);
        });

        String mensagemEsperada = "CARTAO_INVALIDO";
        String mensagemAtual = exception.getMessage();

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string("CARTAO_INVALIDO"));
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }


    @Test
    void testRealizarTransacaoComSenhaInvalida() throws Exception {
        cartaoRequestDto = new CartaoRequestDto("6549873025634501", "12345", 10.0);

        String requestBody = objectMapper.writeValueAsString(cartaoRequestDto);

        Exception exception = assertThrows(SenhaInvalidaException.class, () -> {
            transacaoService.realizarTransacao(cartaoRequestDto);
        });

        String mensagemEsperada = "SENHA_INVALIDA";
        String mensagemAtual = exception.getMessage();

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string("SENHA_INVALIDA"));
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }


    @Test
    void testRealizarTransacaoComValorAcimaDoSaldo() throws Exception {
        cartaoRequestDto = new CartaoRequestDto("6549873025634501", "1234", 10000.0);

        String requestBody = objectMapper.writeValueAsString(cartaoRequestDto);

        Exception exception = assertThrows(SaldoInfucienteException.class, () -> {
            transacaoService.realizarTransacao(cartaoRequestDto);
        });

        String mensagemEsperada = "SALDO_INSUFICIENTE";
        String mensagemAtual = exception.getMessage();

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string("SALDO_INSUFICIENTE"));
        assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

}