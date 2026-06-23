package com.stockMaster.service.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SemDescontoStrategy implements DescontoStrategy {

    @Override
    public BigDecimal calcular(BigDecimal valorTotal, Integer quantidadeItens) {
        return valorTotal;
    }
}