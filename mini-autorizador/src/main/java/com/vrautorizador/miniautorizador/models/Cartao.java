package com.vrautorizador.miniautorizador.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;
    private String senha;
    private double valor = 500.00;

    public Cartao(String numeroCartao, String senha) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
    }

    public Cartao(String numeroCartao, String senha, double valor) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.valor = valor;
    }
}
