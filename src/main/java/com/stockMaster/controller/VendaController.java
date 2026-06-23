package com.stockMaster.controller;

import com.stockMaster.dto.VendaRequestDTO;
import com.stockMaster.dto.VendaResponseDTO;
import com.stockMaster.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<VendaResponseDTO> registrar(
            @RequestBody @Valid VendaRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vendaService.registrarVenda(dto));
    }

    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> listar() {
        return ResponseEntity.ok(vendaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.buscarPorId(id));
    }
}