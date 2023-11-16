package com.vrautorizador.miniautorizador.utils;

import com.vrautorizador.miniautorizador.exceptions.SenhaInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
class SecurityUtilsTest {
    @Test
    void testHashPassword() {
        // Arrange
        String password = "password";

        // Act
        String hashedPassword = SecurityUtils.hashPassword(password);

        // Assert
        assertNotNull(hashedPassword);
        assertNotEquals(password, hashedPassword);
    }

    @Test
    void testIsPasswordValidWithValidPassword() throws SenhaInvalidaException {
        // Arrange
        String storedPassword = SecurityUtils.hashPassword("password");
        String inputPassword = "password";

        // Act
        boolean result = SecurityUtils.isPasswordValid(storedPassword, inputPassword);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsPasswordValidWithInvalidPassword() {
        // Arrange
        String storedPassword = SecurityUtils.hashPassword("password");
        String inputPassword = "wrongPassword";

        // Act and Assert
        assertThrows(SenhaInvalidaException.class, () -> SecurityUtils.isPasswordValid(storedPassword, inputPassword));
    }

}