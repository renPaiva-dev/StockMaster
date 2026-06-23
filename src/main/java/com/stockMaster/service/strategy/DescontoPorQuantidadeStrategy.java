package com.stockMaster.service.strategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import org.springframework.context.annotation.Primary;

@Component
@Primary
public class DescontoPorQuantidadeStrategy implements DescontoStrategy {

    private static final int QUANTIDADE_MINIMA_DESCONTO = 10;
    private static final BigDecimal PERCENTUAL_DESCONTO = BigDecimal.valueOf(0.10);

    @Override
    public BigDecimal calcular(BigDecimal valorTotal, Integer quantidadeItens) {
        if (quantidadeItens >= QUANTIDADE_MINIMA_DESCONTO) {
            BigDecimal desconto = valorTotal.multiply(PERCENTUAL_DESCONTO);
            return valorTotal.subtract(desconto);
        }
        return valorTotal;
    }
}