package com.atl.client_product_service.product.application.usecases.contract;

import com.atl.client_product_service.product.domain.model.ProductDTO;
import reactor.core.publisher.Flux;

public interface IFindAllProductUseCase {
    Flux<ProductDTO> findAll();
}
