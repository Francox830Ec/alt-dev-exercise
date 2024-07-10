package com.atl.client_product_service.product.infrastructure.adapter.out;

import com.atl.client_product_service.product.infrastructure.entity.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface IProductReactivePostgresRepository extends ReactiveCrudRepository<ProductEntity, UUID> {
}
