package com.atl.order_detail_service.order_detail.infrastructure.conf;

import com.atl.order_detail_service.order_detail.application.usecases.command.contract.ICreateOrderUseCase;
import com.atl.order_detail_service.order_detail.application.usecases.command.implementation.CreateOrderUseCaseImpl;
import com.atl.order_detail_service.order_detail.domain.port.out.IOrderCommandReactiveRepository;
import com.atl.order_detail_service.order_detail.domain.port.out.IOrderDetailCommandReactiveRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanOrderConfiguration {
    @Bean
    ICreateOrderUseCase createOrderUseCase(final IOrderCommandReactiveRepository orderRepository,
                                           final IOrderDetailCommandReactiveRepository orderDetailRepository){
        return new CreateOrderUseCaseImpl(orderRepository, orderDetailRepository);
    }
}
