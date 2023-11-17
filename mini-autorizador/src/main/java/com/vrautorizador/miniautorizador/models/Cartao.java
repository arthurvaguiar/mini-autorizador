package com.vrautorizador.miniautorizador.models;

import com.vrautorizador.miniautorizador.exceptions.CartaoInvalidoException;
import com.vrautorizador.miniautorizador.exceptions.SaldoInfucienteException;
import lombok.*;

import javax.persistence.*;


/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
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

    @Column(name = "NUMERO_CARTAO")
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

    public void debitarSaldo(double value) {
        if (value > this.valor) {
            throw new SaldoInfucienteException("SALDO_INSUFICIENTE");
        }
        this.valor -= value;
    }

    public boolean isCardValid(String numeroCartao, String numeroCartaoRequest) {
        boolean valido = false;
        if (!numeroCartao.equals(numeroCartaoRequest)) {
            throw new CartaoInvalidoException("CARTAO_INVALID");
        }
        valido = true;
        return valido;
    }
}
