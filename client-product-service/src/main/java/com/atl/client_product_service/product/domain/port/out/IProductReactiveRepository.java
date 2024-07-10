package com.atl.client_product_service.product.domain.port.out;

import com.atl.client_product_service.product.domain.model.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IProductReactiveRepository {
    Mono<ProductDTO> create(ProductDTO dto);
    Mono<ProductDTO> update(ProductDTO dto);
    Mono<Void> deleteById(UUID uuid);
    Flux<ProductDTO> findAll();
    Mono<ProductDTO> findById(UUID uuid);
    Mono<Boolean> existsById(UUID uuid);
}
