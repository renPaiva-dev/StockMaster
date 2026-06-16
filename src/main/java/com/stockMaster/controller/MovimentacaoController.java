package com.stockMaster.controller;

import com.stockMaster.dto.MovimentacaoRequestDTO;
import com.stockMaster.dto.MovimentacaoResponseDTO;
import com.stockMaster.service.MovimentacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @PostMapping("/entrada")
    public ResponseEntity<MovimentacaoResponseDTO> registrarEntrada(
            @RequestBody @Valid MovimentacaoRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movimentacaoService.registrarEntrada(dto));
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<MovimentacaoResponseDTO>> listarPorProduto(
            @PathVariable Long produtoId) {
        return ResponseEntity.ok(movimentacaoService.listarPorProduto(produtoId));
    }
    @PostMapping("/saida")
    public ResponseEntity<MovimentacaoResponseDTO> registrarSaida(
            @RequestBody @Valid MovimentacaoRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movimentacaoService.registrarSaida(dto));
    }
}