package com.atl.client_product_service.product.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "product")
public record ProductEntity(
        @Id
        @Column("pro_UUID")
        UUID uuid,

        @Column("pro_code")
        String code,

        @Column("pro_name")
        String name,

        @Column("pro_stock")
        Integer stock
) {
}
