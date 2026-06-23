package com.stockMaster.service;

import com.stockMaster.dto.*;
import com.stockMaster.entity.ItemVenda;
import com.stockMaster.entity.Produto;
import com.stockMaster.entity.Venda;
import com.stockMaster.event.EstoqueCriticoEvent;
import com.stockMaster.exception.EstoqueInsuficienteException;
import com.stockMaster.exception.ProdutoNaoEncontradoException;
import com.stockMaster.repository.ProdutoRepository;
import com.stockMaster.repository.VendaRepository;
import com.stockMaster.service.strategy.DescontoStrategy;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final DescontoStrategy descontoStrategy;

    public VendaService(VendaRepository vendaRepository,
                        ProdutoRepository produtoRepository,
                        ApplicationEventPublisher eventPublisher,
                        DescontoStrategy descontoStrategy) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.eventPublisher = eventPublisher;
        this.descontoStrategy = descontoStrategy;
    }

    @Transactional
    public VendaResponseDTO registrarVenda(VendaRequestDTO dto) {

        // 1. Valida disponibilidade de TODOS os itens antes de processar qualquer um (RN05 - atomicidade)
        List<Produto> produtos = new ArrayList<>();
        int quantidadeTotalItens = 0;

        for (ItemVendaRequestDTO itemDto : dto.itens()) {
            Produto produto = produtoRepository.findById(itemDto.produtoId())
                    .orElseThrow(() -> new ProdutoNaoEncontradoException(itemDto.produtoId()));

            if (itemDto.quantidade() > produto.getQuantidade()) {
                throw new EstoqueInsuficienteException(produto.getNome(), produto.getQuantidade());
            }

            produtos.add(produto);
            quantidadeTotalItens += itemDto.quantidade();
        }

        // 2. Calcula valor bruto
        BigDecimal valorBruto = BigDecimal.ZERO;
        for (int i = 0; i < dto.itens().size(); i++) {
            Produto produto = produtos.get(i);
            Integer quantidade = dto.itens().get(i).quantidade();
            valorBruto = valorBruto.add(produto.getPreco().multiply(BigDecimal.valueOf(quantidade)));
        }

        // 3. Aplica desconto via Strategy (OCP - aberto para extensão, fechado para modificação)
        BigDecimal valorFinal = descontoStrategy.calcular(valorBruto, quantidadeTotalItens);

        // 4. Cria a venda
        Venda venda = new Venda();
        venda.setDataHora(LocalDateTime.now());
        venda.setValorTotal(valorFinal);

        List<ItemVenda> itensVenda = new ArrayList<>();

        for (int i = 0; i < dto.itens().size(); i++) {
            Produto produto = produtos.get(i);
            Integer quantidade = dto.itens().get(i).quantidade();

            // Decrementa estoque
            produto.setQuantidade(produto.getQuantidade() - quantidade);
            produtoRepository.save(produto);

            verificarEstoqueCritico(produto);

            ItemVenda item = new ItemVenda();
            item.setQuantidade(quantidade);
            item.setPrecoUnitario(produto.getPreco());
            item.setVenda(venda);
            item.setProduto(produto);

            itensVenda.add(item);
        }

        venda.setItens(itensVenda);
        Venda vendaSalva = vendaRepository.save(venda);

        return toDTO(vendaSalva);
    }

    public List<VendaResponseDTO> listarTodas() {
        return vendaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public VendaResponseDTO buscarPorId(Long id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
        return toDTO(venda);
    }

    private void verificarEstoqueCritico(Produto produto) {
        if (produto.getQuantidade() <= produto.getEstoqueMinimo()) {
            eventPublisher.publishEvent(new EstoqueCriticoEvent(this, produto));
        }
    }

    private VendaResponseDTO toDTO(Venda venda) {
        List<ItemVendaResponseDTO> itensDTO = venda.getItens().stream()
                .map(item -> new ItemVendaResponseDTO(
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getPrecoUnitario(),
                        item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()))
                ))
                .toList();

        return new VendaResponseDTO(
                venda.getId(),
                venda.getDataHora(),
                venda.getValorTotal(),
                itensDTO
        );
    }
}