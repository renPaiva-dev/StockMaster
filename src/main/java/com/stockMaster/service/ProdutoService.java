package com.stockmaster.service;

import com.stockmaster.dto.ProdutoDTO;
import com.stockmaster.entity.Produto;
import com.stockmaster.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto cadastrar(ProdutoDTO dto) {

        Produto produto = new Produto();

        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setQuantidadeDisponivel(dto.quantidadeDisponivel());

        return repository.save(produto);
    }
}
