package com.vrautorizador.miniautorizador.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaoDto {

    @NotBlank
    private String numeroCartao;

    @NotBlank
    private String senha;
}
