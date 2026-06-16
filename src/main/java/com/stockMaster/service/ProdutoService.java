package com.stockMaster.service;

import com.stockMaster.dto.ProdutoRequestDTO;
import com.stockMaster.dto.ProdutoResponseDTO;
import com.stockMaster.entity.Produto;
import com.stockMaster.exception.ProdutoJaCadastradoException;
import com.stockMaster.exception.ProdutoNaoEncontradoException;
import com.stockMaster.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoResponseDTO cadastrar(ProdutoRequestDTO dto) {
        if (produtoRepository.existsByNome(dto.nome())) {
            throw new ProdutoJaCadastradoException(dto.nome());
        }

        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setQuantidade(dto.quantidade());
        produto.setEstoqueMinimo(dto.estoqueMinimo());

        Produto produtoSalvo = produtoRepository.save(produto);
        return toDTO(produtoSalvo);
    }

    public List<ProdutoResponseDTO> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        return toDTO(buscarEntidade(id));
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = buscarEntidade(id);

        if (!produto.getNome().equals(dto.nome()) &&
                produtoRepository.existsByNome(dto.nome())) {
            throw new ProdutoJaCadastradoException(dto.nome());
        }

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setQuantidade(dto.quantidade());
        produto.setEstoqueMinimo(dto.estoqueMinimo());

        return toDTO(produtoRepository.save(produto));
    }

    public void deletar(Long id) {
        buscarEntidade(id);
        produtoRepository.deleteById(id);
    }

    private Produto buscarEntidade(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    private ProdutoResponseDTO toDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getEstoqueMinimo()
        );
    }
}