package com.atl.client_product_service.client.infrastructure.http.service.contract;

import com.atl.client_product_service.client.domain.model.ClientDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IClientReactiveService {
    Mono<ClientDTO> create(ClientDTO dto);
    Mono<ClientDTO> update(UUID id, ClientDTO requestDTO);
    Mono<Void> delete(UUID uuid);
    Mono<ClientDTO> findById(UUID uuid);
    Flux<ClientDTO> findAll();
}
