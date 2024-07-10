package com.atl.order_detail_service.order_detail.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "order_detail")
public record OrderDetailEntity(
        @Id
        @Column("odt_UUID")
        UUID odtUUID,

        @Column("ord_UUID")
        UUID ordUUID,

        @Column("pro_UUID")
        UUID proUUID,

        @Column("odt_unit_price")
        BigDecimal odtUnitPrice
) {
}
