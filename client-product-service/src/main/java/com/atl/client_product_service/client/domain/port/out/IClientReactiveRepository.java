package com.atl.client_product_service.client.domain.port.out;

import com.atl.client_product_service.client.domain.model.ClientDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IClientReactiveRepository {
    Mono<ClientDTO> create(ClientDTO dto);
    Mono<ClientDTO> update(ClientDTO dto);
    Mono<Void> deleteById(UUID id);
    Flux<ClientDTO> findAll();
    Mono<ClientDTO> findById(UUID id);
    Mono<Boolean> existsById(UUID id);
}
