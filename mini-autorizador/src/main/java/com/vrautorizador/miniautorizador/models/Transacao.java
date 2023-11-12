package com.vrautorizador.miniautorizador.models;

import javax.persistence.*;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cartao cartao;

    private String senhaCartao;
    private double valor;

}
