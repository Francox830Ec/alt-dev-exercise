package com.atl.order_detail_service.order_detail.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record OrderResponseDTO(
        UUID ordUUID,
        String ordCode,
        LocalDate ordDate,
        ClientETO client,
        List<OrderDetailETO> orderDetail
) {
}
