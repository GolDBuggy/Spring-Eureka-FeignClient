package com.gateway.spring.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class Router {

    public static final List<String> urls=List.of("/login","/register");

    public Predicate<ServerHttpRequest> securedUrls= url -> urls.stream().noneMatch(uri ->url.getURI().getPath().contains(uri));
}
