package com.atl.order_detail_service.order_detail.infrastructure.adapter.out;

import com.atl.order_detail_service.order_detail.domain.model.OrderDetailDTO;
import com.atl.order_detail_service.order_detail.domain.port.out.IOrderDetailCommandReactiveRepository;
import com.atl.order_detail_service.order_detail.infrastructure.entity.OrderDetailEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderDetailCommandReactivePostgresRepository implements IOrderDetailCommandReactiveRepository {
    private final IOrderDetailCommandReactivePostgresRepository repository;

    public OrderDetailCommandReactivePostgresRepository(IOrderDetailCommandReactivePostgresRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<OrderDetailDTO> createAll(List<OrderDetailDTO> dtoList) {
        return repository.saveAll(dtoList.stream()
                .map(dto -> new OrderDetailEntity(dto.odtUUID(), dto.ordUUID(), dto.proUUID(), dto.odtUnitPrice()))
                .toList())
                .map(entity -> new OrderDetailDTO(entity.odtUUID(), entity.ordUUID(), entity.proUUID(), entity.odtUnitPrice()));
    }

    @Override
    public Mono<Void> deleteByOrdUUID(UUID ordUUID) {
        return repository.deleteByOrdUUID(ordUUID);
    }
}
