package com.stockMaster.controller;

import com.stockMaster.dto.MovimentacaoResponseDTO;
import com.stockMaster.dto.ProdutoEstoqueCriticoDTO;
import com.stockMaster.dto.ResumoVendasDTO;
import com.stockMaster.service.RelatorioService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/estoque-critico")
    public ResponseEntity<List<ProdutoEstoqueCriticoDTO>> estoqueCritico() {
        return ResponseEntity.ok(relatorioService.listarEstoqueCritico());
    }

    @GetMapping("/movimentacoes")
    public ResponseEntity<List<MovimentacaoResponseDTO>> historicoMovimentacoes() {
        return ResponseEntity.ok(relatorioService.historicoMovimentacoes());
    }

    @GetMapping("/movimentacoes/periodo")
    public ResponseEntity<List<MovimentacaoResponseDTO>> historicoPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(relatorioService.historicoMovimentacoesPorPeriodo(inicio, fim));
    }

    @GetMapping("/vendas/resumo")
    public ResponseEntity<ResumoVendasDTO> resumoVendas() {
        return ResponseEntity.ok(relatorioService.resumoVendas());
    }
}