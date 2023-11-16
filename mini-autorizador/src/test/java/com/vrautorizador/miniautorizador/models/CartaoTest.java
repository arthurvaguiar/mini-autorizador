package com.vrautorizador.miniautorizador.models;

import com.vrautorizador.miniautorizador.exceptions.SaldoInfucienteException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
class CartaoTest {


    @Test
    void testDebitarSaldoComSaldoSuficiente() {
        //Prepara
        Cartao cartao = new Cartao("1234567890123456", "1234", 1000.0);

        //Rda
        assertDoesNotThrow(() -> cartao.debitarSaldo(500.0));

        //Verifica
        assertEquals(500.0, cartao.getValor(), 0.01);
    }


    @Test
    void testDebitarSaldoComSaldoInsuficiente() {
        //Prepara
        Cartao cartao = new Cartao("1234567890123456", "senha", 100.0);

        // Roda e verifica
        assertThrows(SaldoInfucienteException.class, () -> cartao.debitarSaldo(500.0));
    }

    @Test
    void testIsCardValidComNumerosIguais() {
        // Prepara
        Cartao cartao = new Cartao("1234567890123456", "senha");

        // Roda e verifica
        assertTrue(cartao.isCardValid("1234567890123456", "1234567890123456"));
    }

    @Test
    void testIsCardValidComNumerosDiferentes() {
        // Prepara
        Cartao cartao = new Cartao("1234567890123456", "senha");

        // Roda e verifica
        assertFalse(cartao.isCardValid("1234567890123456", "6543210987654321"));
    }
}