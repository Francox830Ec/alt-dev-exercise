package com.atl.client_product_service.client.infrastructure.adapter.out;

import com.atl.client_product_service.client.infrastructure.entity.ClientEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface IClientReactivePostgresRepository extends ReactiveCrudRepository<ClientEntity, UUID> {
}
