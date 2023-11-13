package com.vrautorizador.miniautorizador.controllers;

import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.services.ITransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final ITransacaoService transacaoService;


    @Autowired
    public TransacaoController(ITransacaoService transacaoService){
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<?> realizarTransacao( @RequestBody CartaoRequestDto cartaoRequest){
        return  transacaoService.realizarTransacao(cartaoRequest);
    }


}
