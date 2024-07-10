package com.atl.client_product_service.product.application.usecases.implementation;

import com.atl.client_product_service.product.application.usecases.contract.ICreateProductUseCase;
import com.atl.client_product_service.product.domain.model.ProductDTO;
import com.atl.client_product_service.product.domain.port.out.IProductReactiveRepository;
import reactor.core.publisher.Mono;

public class CreateProductUseCaseImpl implements ICreateProductUseCase {
    private final IProductReactiveRepository repository;

    public CreateProductUseCaseImpl(IProductReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ProductDTO> create(ProductDTO dto) {
        return repository.create(dto);
    }
}
