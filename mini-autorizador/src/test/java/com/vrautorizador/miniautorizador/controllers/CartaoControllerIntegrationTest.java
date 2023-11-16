package com.vrautorizador.miniautorizador.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vrautorizador.miniautorizador.models.dto.CartaoDto;
import com.vrautorizador.miniautorizador.services.ICartaoService;
import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CartaoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ICartaoService cartaoService;
    @Autowired
    private ObjectMapper objectMapper;
    private CartaoDto cartaoDto;
    private final String PATH = "/cartoes";


    @Test
    void testObterSaldoDoCartaoComSucesso() throws Exception {

        var numeroCartao = "6549873025634501";

        mockMvc.perform(get(PATH + "/" + numeroCartao)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(500.0)));
    }

    @Test
    void testObterSaldoDoCartaoDeUmCartaoInvalido() throws Exception {
        var numeroCartao = "6549873025634501999";

        mockMvc.perform(get(PATH + "/" + numeroCartao)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCriarNovoCartaoComSucesso() throws Exception {
        cartaoDto = new CartaoDto("654987302563450125555", "1234");

        String requestBody = objectMapper.writeValueAsString(cartaoDto);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroCartao", is(cartaoDto.getNumeroCartao())))
                .andExpect(jsonPath("$.senha", is("A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=")))
                .andExpect(jsonPath("$.valor", is(500.0)));
    }

    @Test
    void testJaExisteCartaoCriadoNoBanco() throws Exception {
        cartaoDto = new CartaoDto("654987302563450125555", "1234");
        String requestBody = objectMapper.writeValueAsString(cartaoDto);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.containsString("Numero do cartao invalido ou ja existe na base de dados. Verifique o numero do cartao.")))
                .andExpect(jsonPath("$.status", is(422)));
    }


    @Test
    void testCriarNovoCartaoInvalido() throws Exception {
        cartaoDto = new CartaoDto("", "1234");
        String requestBody = objectMapper.writeValueAsString(cartaoDto);

        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.containsString("Numero do cartao invalido ou ja existe na base de dados. Verifique o numero do cartao.")))
                .andExpect(jsonPath("$.status", is(422)));
    }
}