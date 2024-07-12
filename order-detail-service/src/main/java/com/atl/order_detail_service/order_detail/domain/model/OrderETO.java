package com.atl.order_detail_service.order_detail.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public record OrderETO(
        UUID ordUUID,
        String ordCode,
        LocalDate ordDate,
        ClientETO client,
        OrderDetailETO orderDetail
) {
}
