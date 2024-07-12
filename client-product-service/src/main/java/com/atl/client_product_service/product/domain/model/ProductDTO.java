package com.atl.client_product_service.product.domain.model;

import java.util.UUID;

public record ProductDTO(
        UUID uuid,
        String code,
        String name,
        Integer stock
) {
}
