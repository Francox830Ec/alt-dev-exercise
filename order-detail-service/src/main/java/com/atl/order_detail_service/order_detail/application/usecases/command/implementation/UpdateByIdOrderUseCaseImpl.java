package com.atl.order_detail_service.order_detail.application.usecases.command.implementation;

import com.atl.order_detail_service.order_detail.application.usecases.command.contract.IUpdateByIdOrderUseCase;
import com.atl.order_detail_service.order_detail.domain.model.OrderDTO;
import com.atl.order_detail_service.order_detail.domain.model.OrderRequestDTO;
import com.atl.order_detail_service.order_detail.domain.port.out.IOrderCommandReactiveRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class UpdateByIdOrderUseCaseImpl implements IUpdateByIdOrderUseCase {
    private final IOrderCommandReactiveRepository commandRepository;

    public UpdateByIdOrderUseCaseImpl(IOrderCommandReactiveRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public Mono<OrderDTO> updateById(UUID ordUUID, OrderRequestDTO requestDTO) {
        return null;
    }
}
