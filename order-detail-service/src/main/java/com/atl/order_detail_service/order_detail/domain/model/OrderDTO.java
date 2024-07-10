package com.atl.order_detail_service.order_detail.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public record OrderDTO(
        UUID ordUUID,
        UUID cliUUID,
        String ordCode,
        LocalDate ordDate
) {
}
