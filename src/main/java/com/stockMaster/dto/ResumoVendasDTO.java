package com.stockMaster.dto;

import java.math.BigDecimal;

public record ResumoVendasDTO(
        Long totalVendas,
        BigDecimal valorTotalAcumulado
) {}