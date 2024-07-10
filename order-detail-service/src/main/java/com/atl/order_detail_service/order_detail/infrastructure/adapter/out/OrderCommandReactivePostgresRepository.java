package com.atl.order_detail_service.order_detail.infrastructure.adapter.out;

import com.atl.order_detail_service.order_detail.domain.model.OrderDTO;
import com.atl.order_detail_service.order_detail.domain.port.out.IOrderCommandReactiveRepository;
import com.atl.order_detail_service.order_detail.infrastructure.entity.OrderEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class OrderCommandReactivePostgresRepository implements IOrderCommandReactiveRepository {
    private final IOrderCommandReactivePostgresRepository repository;

    public OrderCommandReactivePostgresRepository(IOrderCommandReactivePostgresRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<OrderDTO> create(OrderDTO dto) {
        return repository.save(new OrderEntity(dto.ordUUID(), dto.cliUUID(), dto.ordCode(), dto.ordDate()))
                .map(entity -> new OrderDTO(entity.ordUUID(), entity.cliUUID(), entity.ordCode(), entity.ordDate()));
    }
}
