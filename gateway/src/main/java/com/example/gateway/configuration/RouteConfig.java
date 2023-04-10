package com.example.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Neevels
 * @version 1.0
 * @date 2/13/2023 10:34 AM
 */
@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder)  {
        return builder.routes()
                .route("path_route", r -> r.path("/user/**").and().method("POST", "GET", "PUT", "DELETE")
                        .uri("http://localhost:8081"))
                .build();
    }
}
