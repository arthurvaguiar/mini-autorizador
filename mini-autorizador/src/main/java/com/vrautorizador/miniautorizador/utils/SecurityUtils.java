package com.vrautorizador.miniautorizador.utils;


import com.vrautorizador.miniautorizador.exceptions.SenhaInvalidaException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
public abstract class SecurityUtils {

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static boolean isPasswordValid(String storedPassword, String inputPassword) throws SenhaInvalidaException {
        String hashedInputPassword = hashPassword(inputPassword);

        if (!storedPassword.equals(hashedInputPassword)) {
            throw new SenhaInvalidaException("SENHA_INVALIDA");
        }

        return true;
    }
}
