package com.atl.order_detail_service.order_detail.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record OrderResponseDTO(
        UUID ordUUID,
        UUID cliUUID,
        String ordCode,
        LocalDate ordDate,
        List<ProductOrderDetail> productOrderDetailList
) {
}
