package com.stockMaster.event;

import com.stockMaster.entity.Produto;
import org.springframework.context.ApplicationEvent;

public class EstoqueCriticoEvent extends ApplicationEvent {

    private final Produto produto;

    public EstoqueCriticoEvent(Object source, Produto produto) {
        super(source);
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }
}