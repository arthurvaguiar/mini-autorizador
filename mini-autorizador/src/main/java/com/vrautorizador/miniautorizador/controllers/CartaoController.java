package com.vrautorizador.miniautorizador.controllers;

import com.vrautorizador.miniautorizador.models.Cartao;
import com.vrautorizador.miniautorizador.models.dto.CartaoDto;
import com.vrautorizador.miniautorizador.services.ICartaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @author arthur valentim
 * @email arthurvaguiar@gmail.com
 */
@RestController
@RequestMapping("/cartoes")
@Tag(name = "Cartões", description = "Operações relacionadas a cartões")
public class CartaoController {

    private final ICartaoService cartaoService;

    @Autowired
    public CartaoController(ICartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @Operation(summary = "Cria um novo cartão", description = "Criar um novo cartão", tags = {"Cartões"})
    @PostMapping
    public ResponseEntity<Cartao> criarNovoCartao(@RequestBody CartaoDto dto) {
        Cartao cartao = cartaoService.criarOuRetornarExistente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartao);
    }

    @Operation(summary = "Obter saldo do cartão", description = "Obter saldo do cartão", tags = {"Cartões"})
    @GetMapping("/{numeroCartao}")
    public ResponseEntity<Double> obterSaldoDoCartao(@PathVariable @NotBlank String numeroCartao) {
        return ResponseEntity.status(HttpStatus.OK).body(cartaoService.obterSaldoDoCartao(numeroCartao));
    }

}
