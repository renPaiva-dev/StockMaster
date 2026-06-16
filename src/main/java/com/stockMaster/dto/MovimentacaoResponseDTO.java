package com.stockMaster.dto;

import com.stockMaster.enums.TipoMovimentacao;

import java.time.LocalDateTime;

public record MovimentacaoResponseDTO(
        Long id,
        TipoMovimentacao tipo,
        Integer quantidade,
        LocalDateTime dataHora,
        Long produtoId,
        String nomeProduto,
        Integer quantidadeAtualEstoque
) {}