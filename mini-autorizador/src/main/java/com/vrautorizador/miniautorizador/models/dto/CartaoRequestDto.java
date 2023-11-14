package com.vrautorizador.miniautorizador.models.dto;

import com.vrautorizador.miniautorizador.services.factory.validation.CartaoValido;
import lombok.*;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaoRequestDto {

    @CartaoValido
    private String numeroCartao;
    private String senha;
    private double valor;
}
