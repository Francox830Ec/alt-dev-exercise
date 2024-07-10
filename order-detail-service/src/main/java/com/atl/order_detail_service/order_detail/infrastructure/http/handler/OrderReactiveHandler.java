package com.atl.order_detail_service.order_detail.infrastructure.http.handler;

import com.atl.order_detail_service.order_detail.domain.model.OrderRequestDTO;
import com.atl.order_detail_service.order_detail.infrastructure.http.service.contract.IOrderCommandReactiveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class OrderReactiveHandler {
    private final IOrderCommandReactiveService service;

    public OrderReactiveHandler(IOrderCommandReactiveService service) {
        this.service = service;
    }

    public Mono<ServerResponse> create(ServerRequest request){
        return request.bodyToMono(OrderRequestDTO.class)
                .flatMap(service::create)
                .flatMap(response -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(response)))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
    }
}
