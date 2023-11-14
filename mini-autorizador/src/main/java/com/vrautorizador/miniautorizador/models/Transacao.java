package com.vrautorizador.miniautorizador.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
