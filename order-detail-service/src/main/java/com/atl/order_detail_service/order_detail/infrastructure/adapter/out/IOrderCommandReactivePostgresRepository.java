package com.atl.order_detail_service.order_detail.infrastructure.adapter.out;

import com.atl.order_detail_service.order_detail.infrastructure.entity.OrderEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface IOrderCommandReactivePostgresRepository extends ReactiveCrudRepository<OrderEntity, UUID> {
}
