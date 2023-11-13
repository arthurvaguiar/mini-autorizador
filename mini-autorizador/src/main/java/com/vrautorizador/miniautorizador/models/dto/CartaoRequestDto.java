package com.vrautorizador.miniautorizador.models.dto;

import com.vrautorizador.miniautorizador.services.factory.validation.CartaoValido;

public class CartaoRequestDto {
    @CartaoValido
    private String numeroCartao;
    private String senha;
    private double valor;

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
