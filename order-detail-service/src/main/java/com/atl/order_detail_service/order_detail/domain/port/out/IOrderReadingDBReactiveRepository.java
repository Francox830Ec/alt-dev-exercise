package com.atl.order_detail_service.order_detail.domain.port.out;

import com.atl.order_detail_service.order_detail.domain.model.OrderETO;
import com.atl.order_detail_service.order_detail.domain.model.OrderResponseDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IOrderReadingDBReactiveRepository {
    void save (OrderETO eto);
    Mono<OrderResponseDTO> findById(UUID ordUUID);
}
