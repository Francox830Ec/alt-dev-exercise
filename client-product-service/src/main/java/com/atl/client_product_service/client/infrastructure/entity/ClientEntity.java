package com.atl.client_product_service.client.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "client")
public record ClientEntity (
        @Id
        @Column("cli_UUID")
        UUID uuid,

        @Column("cli_name")
        String name,

        @Column("cli_last_name")
        String lastName
) {
}
