package com.stockMaster.service.strategy;

import java.math.BigDecimal;

public interface DescontoStrategy {
    BigDecimal calcular(BigDecimal valorTotal, Integer quantidadeItens);
}