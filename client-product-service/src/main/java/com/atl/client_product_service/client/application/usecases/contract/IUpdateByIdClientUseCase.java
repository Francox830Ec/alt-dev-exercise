package com.atl.client_product_service.client.application.usecases.contract;

import com.atl.client_product_service.client.domain.model.ClientDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IUpdateByIdClientUseCase {
    Mono<ClientDTO> updateById(UUID uuid, ClientDTO dto);
}