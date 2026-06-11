package com.stockmaster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProdutoDTO(

    @NotBlank
    String nome,

    @Positive
    Double preco,

    @Positive
    Integer quantidadeDisponivel

) {}