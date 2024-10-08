package com.atl.order_detail_service.order_detail.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.util.UUID;

public record OrderRequestDTO (
        UUID ordUUID,
        UUID cliUUID,
        String ordCode,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        String ordDate,

        List<ProductOrderDetail> productOrderDetailList
) {
}
