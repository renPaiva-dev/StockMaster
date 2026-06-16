package com.stockMaster.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EstoqueInsuficienteException extends RuntimeException {

    public EstoqueInsuficienteException(String nomeProduto, Integer quantidadeAtual) {
        super("Estoque insuficiente para o produto '" + nomeProduto +
                "'. Quantidade disponível: " + quantidadeAtual);
    }
}