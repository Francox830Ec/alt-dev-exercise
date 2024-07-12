package com.atl.order_detail_service;

import com.atl.order_detail_service.order_detail.application.usecases.command.contract.IReplicateOrderDetailUseCase;
import com.atl.order_detail_service.order_detail.application.usecases.command.implementation.ReplicateOrderDetailUseCaseImpl;
import com.atl.order_detail_service.order_detail.domain.port.out.IOrderReadingDBReactiveRepository;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@Configuration
public class BeanConfiguration {
    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        return initializer;
    }

    @Bean
    IReplicateOrderDetailUseCase replicateOrderDetailUseCase(final IOrderReadingDBReactiveRepository repository){
        return new ReplicateOrderDetailUseCaseImpl(repository);
    }
}
