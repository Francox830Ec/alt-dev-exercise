package com.atl.client_product_service.product.application.usecases.implementation;

import com.atl.client_product_service.product.application.usecases.contract.IFindByIdProductUseCase;
import com.atl.client_product_service.product.domain.model.ProductDTO;
import com.atl.client_product_service.product.domain.port.out.IProductReactiveRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class FindByIdProductUseCaseImpl implements IFindByIdProductUseCase {
    private final IProductReactiveRepository repository;

    public FindByIdProductUseCaseImpl(IProductReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ProductDTO> findById(UUID uuid) {
        return repository.findById(uuid);
    }
}
