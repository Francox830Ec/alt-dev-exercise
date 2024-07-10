package com.atl.client_product_service.product.infrastructure.http.handler;

import com.atl.client_product_service.product.domain.model.ProductDTO;
import com.atl.client_product_service.product.infrastructure.http.service.contract.IProductReactiveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class ProductReactiveHandler {
    private final IProductReactiveService service;

    public ProductReactiveHandler(IProductReactiveService service) {
        this.service = service;
    }

    public Mono<ServerResponse> create(ServerRequest request){
        return request.bodyToMono(ProductDTO.class)
                .flatMap(service::create)
                .flatMap(response -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(response)))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }

    public Mono<ServerResponse> update(ServerRequest request){
        return request.bodyToMono(ProductDTO.class)
                .flatMap(dto -> service.update(UUID.fromString(request.pathVariable("uuid")), dto))
                .flatMap(response -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(response)))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }

    public Mono<ServerResponse> delete(ServerRequest request){
        return service.delete(UUID.fromString(request.pathVariable("uuid")))
                .then(ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        return service.findById(UUID.fromString(request.pathVariable("uuid")))
                .flatMap(response -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(response)))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }

    public Mono<ServerResponse> findAll(ServerRequest request){
        return service.findAll()
                .collectList()
                .flatMap(response -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(response)))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }
}
