package com.stockMaster.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EstoqueCriticoListener {

    @EventListener
    public void aoDetectarEstoqueCritico(EstoqueCriticoEvent event) {
        System.out.println("[ALERTA] Produto '" + event.getProduto().getNome() +
                "' está com estoque crítico. Quantidade atual: " +
                event.getProduto().getQuantidade() +
                " | Mínimo: " + event.getProduto().getEstoqueMinimo());
    }
}