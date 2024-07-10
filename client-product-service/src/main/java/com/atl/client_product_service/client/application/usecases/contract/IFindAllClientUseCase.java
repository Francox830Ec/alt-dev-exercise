package com.atl.client_product_service.client.application.usecases.contract;

import com.atl.client_product_service.client.domain.model.ClientDTO;
import reactor.core.publisher.Flux;

public interface IFindAllClientUseCase {
    Flux<ClientDTO> findAll();
}
