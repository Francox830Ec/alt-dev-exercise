package com.atl.client_product_service.product.application.usecases.implementation;

import com.atl.client_product_service.product.application.usecases.contract.IFindAllProductUseCase;
import com.atl.client_product_service.product.domain.model.ProductDTO;
import com.atl.client_product_service.product.domain.port.out.IProductReactiveRepository;
import reactor.core.publisher.Flux;

public class FindAllProductUseCaseImpl implements IFindAllProductUseCase {
    private final IProductReactiveRepository repository;

    public FindAllProductUseCaseImpl(IProductReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<ProductDTO> findAll() {
        return repository.findAll();
    }
}
