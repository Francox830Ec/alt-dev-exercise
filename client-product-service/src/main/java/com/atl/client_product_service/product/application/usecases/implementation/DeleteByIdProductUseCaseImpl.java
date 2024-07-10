package com.atl.client_product_service.product.application.usecases.implementation;

import com.atl.client_product_service.product.application.usecases.contract.IDeleteByIdProductUseCase;
import com.atl.client_product_service.product.domain.port.out.IProductReactiveRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class DeleteByIdProductUseCaseImpl implements IDeleteByIdProductUseCase {
    private final IProductReactiveRepository repository;

    public DeleteByIdProductUseCaseImpl(IProductReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> deleteById(UUID uuid) {
        return repository.deleteById(uuid);
    }
}
