package com.stockMaster.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProdutoJaCadastradoException extends RuntimeException {

    public ProdutoJaCadastradoException(String nome) {
        super("Produto com nome '" + nome + "' já está cadastrado.");
    }
}