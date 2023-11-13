package com.vrautorizador.miniautorizador.controllers;

import com.vrautorizador.miniautorizador.models.dto.CartaoRequestDto;
import com.vrautorizador.miniautorizador.services.ICartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(name = "/cartoes")
public class CartaoController {

    private final ICartaoService cartaoService;

    @Autowired
    public CartaoController(ICartaoService cartaoService){
        this.cartaoService = cartaoService;
    }

    @PostMapping("/cartoes")
    public ResponseEntity<?> criarNovoCartao(@RequestBody @Valid CartaoRequestDto cartao){
        return cartaoService.criarOuRetornarExistente(cartao);
    }

}
