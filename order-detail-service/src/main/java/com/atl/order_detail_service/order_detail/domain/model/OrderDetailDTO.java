package com.atl.order_detail_service.order_detail.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderDetailDTO(
        UUID odtUUID,
        UUID ordUUID,
        UUID proUUID,
        BigDecimal odtUnitPrice
) {
}
