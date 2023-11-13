package com.vrautorizador.miniautorizador.controllers;

import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.services.ITransacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacoes")
@Api(value = "TransacaoController", description = "Operações relacionadas a transação do cartão")
public class TransacaoController {

    private final ITransacaoService transacaoService;


    @Autowired
    public TransacaoController(ITransacaoService transacaoService){
        this.transacaoService = transacaoService;
    }

    @PostMapping
    @ApiOperation(value = "Realizar transação do cartão", response = ResponseEntity.class)
    public ResponseEntity<?> realizarTransacao( @RequestBody CartaoRequestDto cartaoRequest){
        return  transacaoService.realizarTransacao(cartaoRequest);
    }


}
