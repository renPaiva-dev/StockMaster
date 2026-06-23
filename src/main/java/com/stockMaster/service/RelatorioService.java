package com.stockMaster.service;

import com.stockMaster.dto.MovimentacaoResponseDTO;
import com.stockMaster.dto.ProdutoEstoqueCriticoDTO;
import com.stockMaster.dto.ResumoVendasDTO;
import com.stockMaster.entity.Venda;
import com.stockMaster.repository.MovimentacaoRepository;
import com.stockMaster.repository.ProdutoRepository;
import com.stockMaster.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RelatorioService {

    private final ProdutoRepository produtoRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final VendaRepository vendaRepository;

    public RelatorioService(ProdutoRepository produtoRepository,
                            MovimentacaoRepository movimentacaoRepository,
                            VendaRepository vendaRepository) {
        this.produtoRepository = produtoRepository;
        this.movimentacaoRepository = movimentacaoRepository;
        this.vendaRepository = vendaRepository;
    }

    public List<ProdutoEstoqueCriticoDTO> listarEstoqueCritico() {
        return produtoRepository.findAll().stream()
                .filter(p -> p.getQuantidade() <= p.getEstoqueMinimo())
                .map(p -> new ProdutoEstoqueCriticoDTO(
                        p.getId(),
                        p.getNome(),
                        p.getQuantidade(),
                        p.getEstoqueMinimo()
                ))
                .toList();
    }

    public List<MovimentacaoResponseDTO> historicoMovimentacoes() {
        return movimentacaoRepository.findAll().stream()
                .map(m -> new MovimentacaoResponseDTO(
                        m.getId(),
                        m.getTipo(),
                        m.getQuantidade(),
                        m.getDataHora(),
                        m.getProduto().getId(),
                        m.getProduto().getNome(),
                        m.getProduto().getQuantidade()
                ))
                .toList();
    }

    public List<MovimentacaoResponseDTO> historicoMovimentacoesPorPeriodo(
            LocalDateTime inicio, LocalDateTime fim) {
        return movimentacaoRepository.findAll().stream()
                .filter(m -> !m.getDataHora().isBefore(inicio) && !m.getDataHora().isAfter(fim))
                .map(m -> new MovimentacaoResponseDTO(
                        m.getId(),
                        m.getTipo(),
                        m.getQuantidade(),
                        m.getDataHora(),
                        m.getProduto().getId(),
                        m.getProduto().getNome(),
                        m.getProduto().getQuantidade()
                ))
                .toList();
    }

    public ResumoVendasDTO resumoVendas() {
        List<Venda> vendas = vendaRepository.findAll();

        long totalVendas = vendas.size();
        BigDecimal valorTotal = vendas.stream()
                .map(Venda::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ResumoVendasDTO(totalVendas, valorTotal);
    }
}