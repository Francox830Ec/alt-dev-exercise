package com.atl.client_product_service.client.application.usecases.implementation;

import com.atl.client_product_service.client.application.usecases.contract.IUpdateByIdClientUseCase;
import com.atl.client_product_service.client.domain.model.ClientDTO;
import com.atl.client_product_service.client.domain.port.out.IClientReactiveRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class UpdateByIdClientUseCaseImpl implements IUpdateByIdClientUseCase {
    private final IClientReactiveRepository repository;

    public UpdateByIdClientUseCaseImpl(IClientReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ClientDTO> updateById(UUID uuid, ClientDTO requestDto) {
        return repository.findById(uuid)
                .flatMap(sourceDTO -> repository
                        .update(new ClientDTO(sourceDTO.uuid(), requestDto.name(), requestDto.lastName()))
                        .map(dtoSaved -> new ClientDTO(dtoSaved.uuid(), dtoSaved.name(), dtoSaved.lastName())));
    }
}