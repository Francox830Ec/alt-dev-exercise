package com.atl.client_product_service.client.application.usecases.contract;

import com.atl.client_product_service.client.domain.model.ClientDTO;
import reactor.core.publisher.Mono;

public interface ICreateClientUseCase {
    Mono<ClientDTO> create(ClientDTO dto);
}