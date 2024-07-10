package com.atl.order_detail_service.order_detail.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "order_header")
public record OrderEntity(
        @Id
        @Column("ord_uuid")
        UUID ordUUID,

        @Column("cli_uuid")
        UUID cliUUID,

        @Column("ord_code")
        String ordCode,

        @Column("ord_date")
        LocalDate ordDate
) {
}
