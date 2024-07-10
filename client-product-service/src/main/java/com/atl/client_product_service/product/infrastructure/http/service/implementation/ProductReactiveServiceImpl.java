package com.atl.client_product_service.product.infrastructure.http.service.implementation;

import com.atl.client_product_service.product.application.usecases.contract.*;
import com.atl.client_product_service.product.domain.model.ProductDTO;
import com.atl.client_product_service.product.infrastructure.http.service.contract.IProductReactiveService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Log4j2
public class ProductReactiveServiceImpl implements IProductReactiveService {
    private final ICreateProductUseCase createUseCase;
    private final IUpdateByIdProductUseCase updateByIdUseCase;
    private final IDeleteByIdProductUseCase deleteByIdUseCase;
    private final IFindByIdProductUseCase findByIdUseCase;
    private final IFindAllProductUseCase findAllUseCase;

    public ProductReactiveServiceImpl(ICreateProductUseCase createUseCase,
                                      IUpdateByIdProductUseCase updateByIdUseCase,
                                      IDeleteByIdProductUseCase deleteByIdUseCase,
                                      IFindByIdProductUseCase findByIdUseCase,
                                      IFindAllProductUseCase findAllUseCase) {
        this.createUseCase = createUseCase;
        this.updateByIdUseCase = updateByIdUseCase;
        this.deleteByIdUseCase = deleteByIdUseCase;
        this.findByIdUseCase = findByIdUseCase;
        this.findAllUseCase = findAllUseCase;
    }

    @Override
    public Mono<ProductDTO> create(ProductDTO dto) {
        return createUseCase.create(dto)
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }

    @Override
    public Mono<ProductDTO> update(UUID uuid, ProductDTO dto) {
        return updateByIdUseCase.updateById(uuid, dto)
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return deleteByIdUseCase.deleteById(uuid)
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }

    @Override
    public Mono<ProductDTO> findById(UUID uuid) {
        return findByIdUseCase.findById(uuid)
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }

    @Override
    public Flux<ProductDTO> findAll() {
        return findAllUseCase.findAll()
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable));
    }
}
