package com.vrautorizador.miniautorizador.services.factory;

import com.vrautorizador.miniautorizador.models.Cartao;
import org.springframework.stereotype.Component;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@Component
public class CartaoFactory {

    public Cartao criarCartao(String numeroCartao, String senha) {
        return new Cartao(numeroCartao, senha);
    }
}
