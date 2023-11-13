package com.vrautorizador.miniautorizador.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;
    private String senha;
    private double valor = 500.00;

    public Cartao(String numeroCartao, String senha){
        this.numeroCartao = numeroCartao;
        this.senha = senha;
    }

    public Cartao() {

    }

    public Cartao(String numeroCartao, String senha, double valor) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
