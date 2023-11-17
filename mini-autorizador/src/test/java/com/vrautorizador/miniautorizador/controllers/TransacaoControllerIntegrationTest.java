package com.vrautorizador.miniautorizador.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrautorizador.miniautorizador.exceptions.CartaoInvalidoException;
import com.vrautorizador.miniautorizador.exceptions.SaldoInfucienteException;
import com.vrautorizador.miniautorizador.exceptions.SenhaInvalidaException;
import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.services.ITransacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
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

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private CartaoRequestDto cartaoRequestDto;

    private final String PATH = "/transacoes";

    @DisplayName("Dado que informo dados de um cartão a transação é realizada com sucesso")
    @Test
    void testRealizarTransacaoComSucesso() throws Exception {

        jdbcTemplate.update("INSERT INTO `cartao` (`numero_cartao`, `senha`, `valor`) VALUES ('654987302563450176', 'A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=', '500')");

        cartaoRequestDto = new CartaoRequestDto("654987302563450176", "1234", 10.0);

        String requestBody = objectMapper.writeValueAsString(cartaoRequestDto);

        transacaoService.realizarTransacao(cartaoRequestDto);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Transação realizada com sucesso"));

    }

    @DisplayName("Dado que informo dados de um cartão, com o numero do cartão invalido a transação não é realizada e retorna CARTAO_INVALIDO")
    @Test
    void testRealizarTransacaoComCartaoInvalidO() throws Exception {
        jdbcTemplate.update("INSERT INTO `cartao` (`numero_cartao`, `senha`, `valor`) VALUES ('6549873025634501', 'A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=', '500')");

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
                .andExpect(jsonPath("$.message", is("CARTAO_INVALIDO")))
                .andExpect(jsonPath("$.status", is("UNPROCESSABLE_ENTITY")));
    }



    @DisplayName("Dado que informo dados de um cartão, com a senha do cartão invalida a transação não é realizada e retorna SENHA_INVALIDA")
    @Test
    void testRealizarTransacaoComSenhaInvalida() throws Exception {
        jdbcTemplate.update("INSERT INTO `cartao` (`numero_cartao`, `senha`, `valor`) VALUES ('65498730256345012', 'A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=', '500')");

        cartaoRequestDto = new CartaoRequestDto("65498730256345012", "1235", 10.0);

        String requestBody = objectMapper.writeValueAsString(cartaoRequestDto);

        Exception exception = assertThrows(SenhaInvalidaException.class, () -> {
            transacaoService.realizarTransacao(cartaoRequestDto);
        });


        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", is("SENHA_INVALIDA")))
                .andExpect(jsonPath("$.status", is("UNPROCESSABLE_ENTITY")));
    }



    @DisplayName("Dado que informo dados de um cartão, com o valor a retirar maior que o valor possuido no cadastro do mesmo, a transação não é realizada e retorna SALDO_INSUFICIENTE")
    @Test
    void testRealizarTransacaoComValorAcimaDoSaldo() throws Exception {
        jdbcTemplate.update("INSERT INTO `cartao` (`numero_cartao`, `senha`, `valor`) VALUES ('6549873025634501', 'A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=', '500')");

        cartaoRequestDto = new CartaoRequestDto("6549873025634501", "1234", 10000.0);

        String requestBody = objectMapper.writeValueAsString(cartaoRequestDto);

        Exception exception = assertThrows(SaldoInfucienteException.class, () -> {
            transacaoService.realizarTransacao(cartaoRequestDto);
        });

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message", is("SALDO_INSUFICIENTE")))
                .andExpect(jsonPath("$.status", is("UNPROCESSABLE_ENTITY")));
    }

}