package com.gateway.spring.config;

import com.gateway.spring.filter.JwtFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final JwtFilter jwtFilter;

    public GatewayConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public RouteLocator locator(RouteLocatorBuilder builder) {
        return builder.routes().route("product-server",p->p.path("/product/**").
                filters(f -> f.filter(jwtFilter)).uri("lb://product-server")).build();
    }
}
