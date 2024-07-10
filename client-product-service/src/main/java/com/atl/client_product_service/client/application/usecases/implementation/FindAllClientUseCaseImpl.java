package com.atl.client_product_service.client.application.usecases.implementation;

import com.atl.client_product_service.client.application.usecases.contract.IFindAllClientUseCase;
import com.atl.client_product_service.client.domain.model.ClientDTO;
import com.atl.client_product_service.client.domain.port.out.IClientReactiveRepository;
import reactor.core.publisher.Flux;

public class FindAllClientUseCaseImpl implements IFindAllClientUseCase {
    private final IClientReactiveRepository repository;

    public FindAllClientUseCaseImpl(IClientReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<ClientDTO> findAll() {
        return repository.findAll();
    }
}
