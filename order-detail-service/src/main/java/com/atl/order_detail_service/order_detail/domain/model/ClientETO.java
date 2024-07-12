package com.atl.order_detail_service.order_detail.domain.model;

import java.util.UUID;

public record ClientETO(
        UUID cliUUID,
        String cliNames
) {
}
