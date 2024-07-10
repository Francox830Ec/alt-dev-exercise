package com.atl.client_product_service.client.application.usecases.implementation;

import com.atl.client_product_service.client.application.usecases.contract.IFindByIdClientUseCase;
import com.atl.client_product_service.client.domain.model.ClientDTO;
import com.atl.client_product_service.client.domain.port.out.IClientReactiveRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class FindByIdClientUseCaseImpl implements IFindByIdClientUseCase {
    private final IClientReactiveRepository repository;

    public FindByIdClientUseCaseImpl(IClientReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ClientDTO> findById(UUID uuid) {
        return repository.findById(uuid);
    }
}
