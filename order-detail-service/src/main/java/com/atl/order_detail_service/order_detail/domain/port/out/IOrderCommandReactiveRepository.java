package com.atl.order_detail_service.order_detail.domain.port.out;

import com.atl.order_detail_service.order_detail.domain.model.OrderDTO;
import reactor.core.publisher.Mono;

public interface IOrderCommandReactiveRepository {
    Mono<OrderDTO> create(OrderDTO dto);
}