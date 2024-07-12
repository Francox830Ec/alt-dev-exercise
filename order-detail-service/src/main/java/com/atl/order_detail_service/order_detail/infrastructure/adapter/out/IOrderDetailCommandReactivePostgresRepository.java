package com.atl.order_detail_service.order_detail.infrastructure.adapter.out;

import com.atl.order_detail_service.order_detail.infrastructure.entity.OrderDetailEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IOrderDetailCommandReactivePostgresRepository extends ReactiveCrudRepository<OrderDetailEntity, UUID> {
    Mono<Void> deleteByOrdUUID(UUID ordUUID);
}
