package com.atl.order_detail_service.order_detail.application.usecases.command.contract;

import com.atl.order_detail_service.order_detail.domain.model.OrderDTO;
import com.atl.order_detail_service.order_detail.domain.model.OrderRequestDTO;
import reactor.core.publisher.Mono;

import java.text.ParseException;

public interface ICreateOrderUseCase {
    Mono<OrderDTO> create(OrderRequestDTO dto);
}
