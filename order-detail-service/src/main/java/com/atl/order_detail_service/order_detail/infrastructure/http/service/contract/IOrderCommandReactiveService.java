package com.atl.order_detail_service.order_detail.infrastructure.http.service.contract;

import com.atl.order_detail_service.order_detail.domain.model.OrderDTO;
import com.atl.order_detail_service.order_detail.domain.model.OrderRequestDTO;
import reactor.core.publisher.Mono;

public interface IOrderCommandReactiveService {
    Mono<OrderDTO> create(OrderRequestDTO dto);
}