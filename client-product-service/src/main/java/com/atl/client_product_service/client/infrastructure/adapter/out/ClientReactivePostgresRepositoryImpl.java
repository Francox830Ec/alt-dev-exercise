package com.atl.client_product_service.client.infrastructure.adapter.out;

import com.atl.client_product_service.client.domain.model.ClientDTO;
import com.atl.client_product_service.client.domain.port.out.IClientReactiveRepository;
import com.atl.client_product_service.client.infrastructure.entity.ClientEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class ClientReactivePostgresRepositoryImpl implements IClientReactiveRepository {
    private final IClientReactivePostgresRepository repository;

    public ClientReactivePostgresRepositoryImpl(IClientReactivePostgresRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ClientDTO> create(ClientDTO dto) {
        return repository.save(new ClientEntity(dto.uuid(), dto.name(), dto.lastName()))
                .map(clientEntity -> new ClientDTO(clientEntity.uuid(), clientEntity.name(), clientEntity.lastName()));
    }

    @Override
    public Mono<ClientDTO> update(ClientDTO dto) {
        return create(dto);
    }

    @Override
    public Mono<Void> deleteById(UUID uuid) {
        return repository.deleteById(uuid);
    }

    @Override
    public Flux<ClientDTO> findAll() {
        return repository.findAll()
                .map(clientEntity -> new ClientDTO(clientEntity.uuid(), clientEntity.name(), clientEntity.lastName()));
    }

    @Override
    public Mono<ClientDTO> findById(UUID uuid) {
        return repository.findById(uuid)
                .map(clientEntity -> new ClientDTO(clientEntity.uuid(), clientEntity.name(), clientEntity.lastName()));
    }

    @Override
    public Mono<Boolean> existsById(UUID uuid) {
        return repository.existsById(uuid);
    }
}
