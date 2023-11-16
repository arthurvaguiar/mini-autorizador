package com.vrautorizador.miniautorizador.services.factory;

import com.vrautorizador.miniautorizador.models.Cartao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
class CartaoFactoryTest {

    @Test
    void testCriarCartao() {
        // Arrange
        String numeroCartao = "1234567890123456";
        String senha = "senha";
        CartaoFactory cartaoFactory = new CartaoFactory();

        // Act
        Cartao result = cartaoFactory.criarCartao(numeroCartao, senha);

        // Assert
        assertNotNull(result);
        assertEquals(numeroCartao, result.getNumeroCartao());
        assertEquals(senha, result.getSenha());
        assertEquals(500.0, result.getValor());
    }

}
