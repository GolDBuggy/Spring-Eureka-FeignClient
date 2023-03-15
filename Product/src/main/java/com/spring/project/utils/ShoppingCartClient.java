package com.spring.project.utils;

import com.spring.project.dto.ShoppingCartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cart-service",path = "/shopping/cart")
public interface ShoppingCartClient {

    @GetMapping("/{userId}")
    ResponseEntity<List<ShoppingCartDto>> getAll(@PathVariable(name = "userId") String id);


}
