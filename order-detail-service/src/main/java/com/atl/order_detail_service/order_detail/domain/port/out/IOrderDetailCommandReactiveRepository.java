package com.atl.order_detail_service.order_detail.domain.port.out;

import com.atl.order_detail_service.order_detail.domain.model.OrderDetailDTO;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IOrderDetailCommandReactiveRepository {
    Flux<OrderDetailDTO> createAll(List<OrderDetailDTO> dtoList);
}
