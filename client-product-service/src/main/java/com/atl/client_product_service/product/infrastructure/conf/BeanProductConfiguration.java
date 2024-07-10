package com.atl.client_product_service.product.infrastructure.conf;

import com.atl.client_product_service.product.application.usecases.contract.*;
import com.atl.client_product_service.product.application.usecases.implementation.*;
import com.atl.client_product_service.product.domain.port.out.IProductReactiveRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanProductConfiguration {
    @Bean
    ICreateProductUseCase createProductUseCase(final IProductReactiveRepository repository) {
        return new CreateProductUseCaseImpl(repository);
    }

    @Bean
    IUpdateByIdProductUseCase updateByIdUseCaseImpl(final IProductReactiveRepository repository) {
        return new UpdateByIdUseCaseImpl(repository);
    }

    @Bean
    IDeleteByIdProductUseCase deleteByIdProductUseCase(final IProductReactiveRepository repository) {
        return new DeleteByIdProductUseCaseImpl(repository);
    }

    @Bean
    IFindByIdProductUseCase findByIdProductUseCase(final IProductReactiveRepository repository) {
        return new FindByIdProductUseCaseImpl(repository);
    }

    @Bean
    IFindAllProductUseCase findAllProductUseCase(final IProductReactiveRepository repository) {
        return new FindAllProductUseCaseImpl(repository);
    }
}
