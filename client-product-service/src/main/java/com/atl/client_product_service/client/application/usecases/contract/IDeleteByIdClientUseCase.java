package com.atl.client_product_service.client.application.usecases.contract;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IDeleteByIdClientUseCase {
    Mono<Void> deleteById(UUID uuid);
}
