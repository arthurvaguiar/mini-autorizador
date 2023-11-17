package com.vrautorizador.miniautorizador.controllers;

import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.services.ITransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@RestController
@RequestMapping("/transacoes")
@Tag(name = "Transações", description = "Operações relacionadas a transação do cartão")
public class TransacaoController {
    private final ITransacaoService transacaoService;

    @Autowired
    public TransacaoController(ITransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @Operation(summary = "Realizar transação", description = "Realizar transação", tags = {"Transações"})
    @PostMapping
    public ResponseEntity<Object> realizarTransacao(@RequestBody CartaoRequestDto cartaoRequest) {
        this.transacaoService.realizarTransacao(cartaoRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Transação realizada com sucesso");
    }
}
