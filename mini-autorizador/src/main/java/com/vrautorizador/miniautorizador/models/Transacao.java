package com.vrautorizador.miniautorizador.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SENHA_CARTAO")
    private String senhaCartao;
    private double valor;
    @ManyToOne
    @JoinColumn(name = "CARTAO_ID")
    private Cartao cartao;


    public Transacao(Cartao cartao, String senha, double valor) {
        this.cartao = cartao;
        this.senhaCartao = senha;
        this.valor = valor;
    }
}
