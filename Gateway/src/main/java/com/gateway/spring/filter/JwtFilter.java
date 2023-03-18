package com.gateway.spring.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtFilter implements GatewayFilter{


    private final Router router;
    private final JwtService jwtService;

    public JwtFilter(Router router, JwtService jwtService) {
        this.router = router;
        this.jwtService = jwtService;
    }




    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token=null;

        if(router.securedUrls.test(exchange.getRequest())){

            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                throw new RuntimeException("Header not found!");
            }

            String auth=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if (auth!=null&& auth.startsWith("Bearer ")){
                token =auth.substring(7);
            }

            if(!jwtService.validateToken(token)){
                throw new RuntimeException("Token is invalid!");
            }


        }
        return chain.filter(exchange);

    }




}




