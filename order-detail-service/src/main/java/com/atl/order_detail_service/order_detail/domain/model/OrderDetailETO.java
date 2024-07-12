package com.atl.order_detail_service.order_detail.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderDetailETO(
        UUID odtUUID,
        UUID proUUID,
        String proName,
        String proCode,
        BigDecimal odtUnitPrice
) {
}
