package com.vrautorizador.miniautorizador.controllers;

import com.vrautorizador.miniautorizador.models.dto.CartaoDto;
import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.services.ICartaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cartoes")
@Api(value = "CartaoController", description = "Operações relacionadas ao cartão")
public class CartaoController {

    private final ICartaoService cartaoService;

    @Autowired
    public CartaoController(ICartaoService cartaoService){
        this.cartaoService = cartaoService;
    }

    @PostMapping
    @ApiOperation(value = "Cria um novo cartão", response = ResponseEntity.class)
    public ResponseEntity<?> criarNovoCartao(@RequestBody @Valid CartaoDto cartao){
        return cartaoService.criarOuRetornarExistente(cartao);
    }

    @GetMapping("/{numeroCartao}")
    @ApiOperation(value = "Retorna o saldo do cartão", response = ResponseEntity.class)
    public ResponseEntity<?> obterSaldoDoCartao(@PathVariable @Valid String numeroCartao){
        return cartaoService.obterSaldoDoCartao(numeroCartao);
    }



}
