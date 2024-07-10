package com.atl.client_product_service.client.domain.model;

import java.util.UUID;

public record ClientDTO(
        UUID uuid,
        String name,
        String lastName
) {
}
