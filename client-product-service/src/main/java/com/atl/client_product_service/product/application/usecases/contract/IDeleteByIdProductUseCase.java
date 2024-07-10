package com.atl.client_product_service.product.application.usecases.contract;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IDeleteByIdProductUseCase {
    Mono<Void> deleteById(UUID uuid);
}
