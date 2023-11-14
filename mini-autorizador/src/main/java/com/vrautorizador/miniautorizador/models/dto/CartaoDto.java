package com.vrautorizador.miniautorizador.models.dto;

import com.vrautorizador.miniautorizador.services.factory.validation.CartaoValido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaoDto {
    @CartaoValido
    private String numeroCartao;
    private String senha;
}
