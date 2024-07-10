package com.atl.client_product_service.product.application.usecases.contract;

import com.atl.client_product_service.product.domain.model.ProductDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IUpdateByIdProductUseCase {
    Mono<ProductDTO> updateById(UUID uuid, ProductDTO dto);
}
