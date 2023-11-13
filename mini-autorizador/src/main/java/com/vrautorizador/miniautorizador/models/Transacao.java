package com.vrautorizador.miniautorizador.models;

import javax.persistence.*;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    private String senhaCartao;
    private double valor;

    public Transacao(Cartao cartao, String senha, double valor) {
        this.cartao = cartao;
        this.senhaCartao = senha;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public String getSenhaCartao() {
        return senhaCartao;
    }

    public void setSenhaCartao(String senhaCartao) {
        this.senhaCartao = senhaCartao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
