package com.stockmaster.controller;

import com.stockmaster.dto.ProdutoDTO;
import com.stockmaster.entity.Produto;
import com.stockmaster.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public Produto cadastrar(@RequestBody @Valid ProdutoDTO dto) {
        return service.cadastrar(dto);
    }
}