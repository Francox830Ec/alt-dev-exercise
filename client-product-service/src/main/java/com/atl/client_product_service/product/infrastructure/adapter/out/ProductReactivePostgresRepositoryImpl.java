package com.atl.client_product_service.product.infrastructure.adapter.out;

import com.atl.client_product_service.product.domain.model.ProductDTO;
import com.atl.client_product_service.product.domain.port.out.IProductReactiveRepository;
import com.atl.client_product_service.product.infrastructure.entity.ProductEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class ProductReactivePostgresRepositoryImpl implements IProductReactiveRepository {
    private final IProductReactivePostgresRepository repository;

    public ProductReactivePostgresRepositoryImpl(IProductReactivePostgresRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ProductDTO> create(ProductDTO dto) {
        return repository.save(new ProductEntity(dto.uuid(), dto.code(), dto.name()))
                .map(entity -> new ProductDTO(entity.uuid(), entity.code(), entity.name()));
    }

    @Override
    public Mono<ProductDTO> update(ProductDTO dto) {
        return create(dto);
    }

    @Override
    public Mono<Void> deleteById(UUID uuid) {
        return repository.deleteById(uuid);
    }

    @Override
    public Flux<ProductDTO> findAll() {
        return repository.findAll()
                .map(entity -> new ProductDTO(entity.uuid(), entity.code(), entity.name()));
    }

    @Override
    public Mono<ProductDTO> findById(UUID uuid) {
        return repository.findById(uuid)
                .map(entity -> new ProductDTO(entity.uuid(), entity.code(), entity.name()));
    }

    @Override
    public Mono<Boolean> existsById(UUID uuid) {
        return repository.existsById(uuid);
    }
}
