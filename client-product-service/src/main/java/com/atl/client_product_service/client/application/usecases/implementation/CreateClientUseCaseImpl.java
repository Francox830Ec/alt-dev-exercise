package com.atl.client_product_service.client.application.usecases.implementation;

import com.atl.client_product_service.client.application.usecases.contract.ICreateClientUseCase;
import com.atl.client_product_service.client.domain.model.ClientDTO;
import com.atl.client_product_service.client.domain.port.out.IClientReactiveRepository;
import reactor.core.publisher.Mono;

public class CreateClientUseCaseImpl implements ICreateClientUseCase {
    private final IClientReactiveRepository repository;

    public CreateClientUseCaseImpl(IClientReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ClientDTO> create(ClientDTO dto) {
        return repository.create(dto);
    }
}
