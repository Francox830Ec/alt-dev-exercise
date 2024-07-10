package com.atl.client_product_service.client.infrastructure.conf;

import com.atl.client_product_service.client.application.usecases.contract.*;
import com.atl.client_product_service.client.application.usecases.implementation.*;
import com.atl.client_product_service.client.domain.port.out.IClientReactiveRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanClientConfiguration{
    @Bean
    ICreateClientUseCase createClientUseCase(final IClientReactiveRepository repository){
        return new CreateClientUseCaseImpl(repository);
    }

    @Bean
    IUpdateByIdClientUseCase updateClientByIdUseCase(final IClientReactiveRepository repository){
        return new UpdateByIdClientUseCaseImpl(repository);
    }

    @Bean
    IDeleteByIdClientUseCase deleteClientByIdUseCase(final IClientReactiveRepository repository){
        return new DeleteByIdClientUseCaseImpl(repository);
    }

    @Bean
    IFindByIdClientUseCase findByIdClientUseCase(final IClientReactiveRepository repository){
        return new FindByIdClientUseCaseImpl(repository);
    }

    @Bean
    IFindAllClientUseCase findAllClientUseCase(final IClientReactiveRepository repository){
        return new FindAllClientUseCaseImpl(repository);
    }
}