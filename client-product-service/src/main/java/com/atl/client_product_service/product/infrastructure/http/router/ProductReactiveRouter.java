package com.atl.client_product_service.product.infrastructure.http.router;

import com.atl.client_product_service.product.infrastructure.http.handler.ProductReactiveHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.atl.client_product_service.product.infrastructure.constant.ProductConstant.BASE_PATH;
import static com.atl.client_product_service.product.infrastructure.constant.ProductConstant.ID_PARAM;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class ProductReactiveRouter {
    @Bean
    public RouterFunction<ServerResponse> productRoute(ProductReactiveHandler handler) {
        return RouterFunctions
                .route(GET(BASE_PATH + ID_PARAM)
                        .and(accept(MediaType.APPLICATION_JSON)), handler::findById)
                .andRoute(GET(BASE_PATH)
                        .and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
                .andRoute(POST(BASE_PATH)
                        .and(accept(MediaType.APPLICATION_JSON)), handler::create)
                .andRoute(PUT(BASE_PATH + ID_PARAM)
                        .and(accept(MediaType.APPLICATION_JSON)), handler::update)
                .andRoute(DELETE(BASE_PATH + ID_PARAM)
                        .and(accept(MediaType.APPLICATION_JSON)), handler::delete);
    }
}
