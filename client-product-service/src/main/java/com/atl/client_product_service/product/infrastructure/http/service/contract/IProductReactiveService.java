package com.atl.client_product_service.product.infrastructure.http.service.contract;

import com.atl.client_product_service.product.domain.model.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IProductReactiveService {
    Mono<ProductDTO> create(ProductDTO dto);
    Mono<ProductDTO> update(UUID uuid, ProductDTO dto);
    Mono<Void> delete(UUID uuid);
    Mono<ProductDTO> findById(UUID uuid);
    Flux<ProductDTO> findAll();
}
