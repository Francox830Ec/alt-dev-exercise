package com.atl.order_detail_service.order_detail.infrastructure.hash;

import com.atl.order_detail_service.order_detail.domain.model.ClientETO;
import com.atl.order_detail_service.order_detail.domain.model.OrderDetailETO;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RedisHash(value = "orders")
@TypeAlias("order")
public record OrderHash(
        @Id
        @Indexed
        UUID ordUUID,
        String ordCode,
        String ordDate,
        ClientETO client,
        List<OrderDetailETO> orderDetail
) {
}
