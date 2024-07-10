package com.atl.order_detail_service.order_detail.infrastructure.http.router;

import com.atl.order_detail_service.order_detail.infrastructure.http.handler.OrderReactiveHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.atl.order_detail_service.order_detail.infrastructure.constant.OrderConstant.BASE_PATH;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class OrderReactiveRouter {
    @Bean
    public RouterFunction<ServerResponse> orderRoute(OrderReactiveHandler handler) {
        return RouterFunctions
                .route(POST(BASE_PATH)
                        .and(accept(MediaType.APPLICATION_JSON)), handler::create);
    }
}
