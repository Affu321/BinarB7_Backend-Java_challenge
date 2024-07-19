package com.gateway.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("order-service", r -> r.path("/v1/order/**")
                        .uri("http://localhost:8082/"))
                .route("user-service", r -> r.path("/v1/user/**")
                        .uri("http://localhost:8088/"))
                .route("product-service", r -> r.path("/v1/product/**")
                        .uri("http://localhost:8082/"))
                .build();
    }
}
