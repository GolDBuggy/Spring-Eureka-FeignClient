package com.gateway.spring.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class JwtFilter extends AbstractGatewayFilterFactory<JwtFilter.Config> {


    private final Router router;
    private final JwtService jwtService;

    public JwtFilter(Router router, JwtService jwtService) {
        super(Config.class);
        this.router = router;
        this.jwtService = jwtService;
    }


    @Override
    public GatewayFilter apply(Config config) {
        final String[] token={null};
        return ((exchange, chain) ->{
            if(router.securedUrls.test(exchange.getRequest())){

                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Header not found!");
                }

                String auth=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (auth!=null&& auth.startsWith("Bearer ")){
                    token[0] =auth.substring(7);
                }

                if(!jwtService.validateToken(token[0])){
                    throw new RuntimeException("Token is invalid!");
                }


            }
            return chain.filter(exchange);
        });
    }


    public static class Config{

    }


}




