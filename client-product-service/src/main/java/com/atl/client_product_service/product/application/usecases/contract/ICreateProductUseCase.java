package com.atl.client_product_service.product.application.usecases.contract;

import com.atl.client_product_service.product.domain.model.ProductDTO;
import reactor.core.publisher.Mono;

public interface ICreateProductUseCase {
    Mono<ProductDTO> create(ProductDTO dto);
}
