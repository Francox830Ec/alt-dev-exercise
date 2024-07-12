package com.atl.order_detail_service.order_detail.infrastructure.adapter.out;

import com.atl.order_detail_service.order_detail.domain.model.OrderDetailETO;
import com.atl.order_detail_service.order_detail.domain.model.OrderETO;
import com.atl.order_detail_service.order_detail.domain.model.OrderResponseDTO;
import com.atl.order_detail_service.order_detail.domain.port.out.IOrderReadingDBReactiveRepository;
import com.atl.order_detail_service.order_detail.infrastructure.hash.OrderHash;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Repository
public class OrderRedisReactiveRepositoryImpl implements IOrderReadingDBReactiveRepository {
    private final ReactiveHashOperations<String, String, OrderHash> reactiveHashOperations;

    public OrderRedisReactiveRepositoryImpl(ReactiveHashOperations<String, String, OrderHash> reactiveHashOperations) {
        this.reactiveHashOperations = reactiveHashOperations;
    }

    @Override
    public void save(OrderETO eto) {
        Boolean haveOrders = reactiveHashOperations.hasKey("orders", hashKey).block();
        if(!haveOrders){
            List<OrderDetailETO> orderDetailList = new ArrayList<>();
            orderDetailList.add(eto.orderDetail());
            buildOrders(orderDetailList, eto);
        }else {
                findById(eto.ordUUID())
                    .mapNotNull(orderResponseDTO -> {
                        List<OrderDetailETO> orderDetailIniList = orderResponseDTO.orderDetail();
                        orderDetailIniList.add(eto.orderDetail());
                        buildOrders(orderDetailIniList, eto);
                        return orderDetailIniList;
                    }).subscribe();
        }
    }

    private void buildOrders(List<OrderDetailETO> orderDetailList, OrderETO eto){
        reactiveHashOperations.put("orders", hashKey,
                        new OrderHash(eto.ordUUID(), eto.ordCode(), eto.ordDate().toString(), eto.client(),
                                orderDetailList ))
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(aBoolean -> log.info("Saved in REDIS: {}", eto.toString()))
                .doOnError(err -> log.error("Error while try to save plate in REDIS: {}", err.getMessage()))
                .subscribe();
    }

    @Override
    public Mono<OrderResponseDTO> findById(UUID ordUUID) {
        return reactiveHashOperations.get("orders", ordUUID.toString())
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(orderHash -> log.info("Reading of REDIS - {}", orderHash.toString()))
                .doOnError(err -> log.error("Error while try to find order in REDIS: {}", err.getMessage()))
                .onErrorResume(throwable -> Mono.empty())
                .map(orderHash -> new OrderResponseDTO(orderHash.ordUUID(), orderHash.ordCode(),
                        LocalDate.parse(orderHash.ordDate()),
                        orderHash.client(), orderHash.orderDetail()));
    }
}
