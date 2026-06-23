package com.stockMaster.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Preço é obrigatório")
        @DecimalMin(value = "0.01")
        BigDecimal preco,

        @NotNull(message = "Quantidade é obrigatória")
        @Min(0)
        Integer quantidade,
        String descricao,
        @NotNull(message = "Estoque mínimo é obrigatório")
        @Min(value = 0, message = "Estoque mínimo não pode ser negativo")
        Integer estoqueMinimo
) {
}