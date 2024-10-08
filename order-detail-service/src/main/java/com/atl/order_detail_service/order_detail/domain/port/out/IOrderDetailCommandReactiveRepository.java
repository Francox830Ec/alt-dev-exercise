package com.atl.order_detail_service.order_detail.domain.port.out;

import com.atl.order_detail_service.order_detail.domain.model.OrderDetailDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface IOrderDetailCommandReactiveRepository {
    Flux<OrderDetailDTO> createAll(List<OrderDetailDTO> dtoList);
    Mono<Void> deleteByOrdUUID(UUID ordUUID);
}
