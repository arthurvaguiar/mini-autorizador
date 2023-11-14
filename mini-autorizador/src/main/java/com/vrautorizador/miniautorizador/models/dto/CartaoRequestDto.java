package com.vrautorizador.miniautorizador.models.dto;

import com.vrautorizador.miniautorizador.services.factory.validation.CartaoValido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartaoRequestDto {
    @CartaoValido
    private String numeroCartao;
    private String senha;
    private double valor;
}
