package com.atl.client_product_service.client.application.usecases.implementation;

import com.atl.client_product_service.client.application.usecases.contract.IDeleteByIdClientUseCase;
import com.atl.client_product_service.client.domain.port.out.IClientReactiveRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class DeleteByIdClientUseCaseImpl implements IDeleteByIdClientUseCase {
    private final IClientReactiveRepository repository;

    public DeleteByIdClientUseCaseImpl(IClientReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> deleteById(UUID uuid) {
        return repository.deleteById(uuid);
    }
}
