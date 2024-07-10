package com.atl.client_product_service.product.application.usecases.implementation;

import com.atl.client_product_service.product.application.usecases.contract.IUpdateByIdProductUseCase;
import com.atl.client_product_service.product.domain.model.ProductDTO;
import com.atl.client_product_service.product.domain.port.out.IProductReactiveRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class UpdateByIdUseCaseImpl implements IUpdateByIdProductUseCase {
    private final IProductReactiveRepository repository;

    public UpdateByIdUseCaseImpl(IProductReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ProductDTO> updateById(UUID uuid, ProductDTO requestDto) {
        return repository.findById(uuid)
                .flatMap(sourceDTO -> repository
                        .update(new ProductDTO(sourceDTO.uuid(), requestDto.code(), requestDto.name()))
                        .map(dtoSaved -> new ProductDTO(dtoSaved.uuid(), dtoSaved.code(), dtoSaved.name())));
    }
}