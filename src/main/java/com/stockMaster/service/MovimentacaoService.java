package com.stockMaster.service;

import com.stockMaster.dto.MovimentacaoRequestDTO;
import com.stockMaster.dto.MovimentacaoResponseDTO;
import com.stockMaster.entity.Movimentacao;
import com.stockMaster.entity.Produto;
import com.stockMaster.enums.TipoMovimentacao;
import com.stockMaster.exception.EstoqueInsuficienteException;
import com.stockMaster.exception.ProdutoNaoEncontradoException;
import com.stockMaster.repository.MovimentacaoRepository;
import com.stockMaster.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository,
                               ProdutoRepository produtoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public MovimentacaoResponseDTO registrarEntrada(MovimentacaoRequestDTO dto) {
        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException(dto.produtoId()));

        produto.setQuantidade(produto.getQuantidade() + dto.quantidade());
        produtoRepository.save(produto);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipo(TipoMovimentacao.ENTRADA);
        movimentacao.setQuantidade(dto.quantidade());
        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setProduto(produto);

        Movimentacao salva = movimentacaoRepository.save(movimentacao);
        return toDTO(salva);
    }
    @Transactional
    public MovimentacaoResponseDTO registrarSaida(MovimentacaoRequestDTO dto) {
        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException(dto.produtoId()));

        if (dto.quantidade() > produto.getQuantidade()) {
            throw new EstoqueInsuficienteException(produto.getNome(), produto.getQuantidade());
        }

        produto.setQuantidade(produto.getQuantidade() - dto.quantidade());
        produtoRepository.save(produto);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipo(TipoMovimentacao.SAIDA);
        movimentacao.setQuantidade(dto.quantidade());
        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setProduto(produto);

        Movimentacao salva = movimentacaoRepository.save(movimentacao);
        return toDTO(salva);
    }

    public List<MovimentacaoResponseDTO> listarPorProduto(Long produtoId) {
        produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));

        return movimentacaoRepository.findByProdutoId(produtoId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private MovimentacaoResponseDTO toDTO(Movimentacao m) {
        return new MovimentacaoResponseDTO(
                m.getId(),
                m.getTipo(),
                m.getQuantidade(),
                m.getDataHora(),
                m.getProduto().getId(),
                m.getProduto().getNome(),
                m.getProduto().getQuantidade()
        );
    }
}