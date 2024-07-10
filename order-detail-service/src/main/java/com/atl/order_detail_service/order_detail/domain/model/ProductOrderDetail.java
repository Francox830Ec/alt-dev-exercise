package com.atl.order_detail_service.order_detail.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductOrderDetail(
        UUID proUUID,
        BigDecimal odtUnitPrice
) {
}
