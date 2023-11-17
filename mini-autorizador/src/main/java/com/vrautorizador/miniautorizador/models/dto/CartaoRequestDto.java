package com.vrautorizador.miniautorizador.models.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;


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

    @NotBlank
    private String numeroCartao;

    @NotBlank
    private String senha;
    private double valor;
}
