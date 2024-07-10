package com.atl.order_detail_service.order_detail.infrastructure.http.service.implementation;

import com.atl.order_detail_service.order_detail.application.usecases.command.contract.ICreateOrderUseCase;
import com.atl.order_detail_service.order_detail.domain.model.OrderDTO;
import com.atl.order_detail_service.order_detail.domain.model.OrderRequestDTO;
import com.atl.order_detail_service.order_detail.infrastructure.http.service.contract.IOrderCommandReactiveService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class OrderCommandReactiveServiceImpl implements IOrderCommandReactiveService {
    private final ICreateOrderUseCase createUseCase;

    public OrderCommandReactiveServiceImpl(ICreateOrderUseCase createUseCase) {
        this.createUseCase = createUseCase;
    }

    @Override
    public Mono<OrderDTO> create(OrderRequestDTO dto) {
        return createUseCase.create(dto)
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }
}
