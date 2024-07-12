package com.atl.order_detail_service.order_detail.application.usecases.command.implementation;

import com.atl.order_detail_service.order_detail.application.usecases.command.contract.ICreateOrderUseCase;
import com.atl.order_detail_service.order_detail.domain.model.OrderDTO;
import com.atl.order_detail_service.order_detail.domain.model.OrderDetailDTO;
import com.atl.order_detail_service.order_detail.domain.model.OrderRequestDTO;
import com.atl.order_detail_service.order_detail.domain.port.out.IOrderCommandReactiveRepository;
import com.atl.order_detail_service.order_detail.domain.port.out.IOrderDetailCommandReactiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreateOrderUseCaseImpl implements ICreateOrderUseCase {
    private final IOrderCommandReactiveRepository orderRepository;
    private final IOrderDetailCommandReactiveRepository orderDetailRepository;

    public CreateOrderUseCaseImpl(IOrderCommandReactiveRepository orderRepository,
                                  IOrderDetailCommandReactiveRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }


    @Override
    public Mono<OrderDTO> create(OrderRequestDTO requestDTO) {
        return orderRepository
                .create(new OrderDTO(requestDTO.ordUUID(), requestDTO.cliUUID(), requestDTO.ordCode(),
                        LocalDate.parse(requestDTO.ordDate())))
                .zipWhen(orderDTO -> createAllOrderDetail(orderDTO.ordUUID(), requestDTO).collectList())
                .map(Tuple2::getT1);
    }

    private List<OrderDetailDTO> buildOrderDetail (OrderRequestDTO requestDTO){
        return requestDTO.productOrderDetailList()
                .stream()
                .map(product -> new OrderDetailDTO(null, requestDTO.ordUUID(), product.proUUID(), product.odtUnitPrice()))
                .toList();
    }

    private Flux<OrderDetailDTO> saveALLOrderDetail(List<OrderDetailDTO> orderDetailList){
        return orderDetailRepository.createAll(orderDetailList);
    }

    private Flux<OrderDetailDTO>  createAllOrderDetail(UUID ordUUID, OrderRequestDTO requestDTO){
        List<OrderDetailDTO> orderDetailDTOList = buildOrderDetail(new OrderRequestDTO(ordUUID, requestDTO.cliUUID(),
                requestDTO.ordCode(),requestDTO.ordDate(), requestDTO.productOrderDetailList()));
        return saveALLOrderDetail(orderDetailDTOList);
    }
}