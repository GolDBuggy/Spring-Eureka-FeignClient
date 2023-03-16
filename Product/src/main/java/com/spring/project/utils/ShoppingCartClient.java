package com.spring.project.utils;

import com.spring.project.dto.ErrorDto;
import com.spring.project.dto.ShoppingCartDto;
import com.spring.project.exception.ErrorResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;


@FeignClient(name = "cart-service",path = "/shopping/cart")
public interface ShoppingCartClient {

    Logger logger=Logger.getLogger(ShoppingCartClient.class.getName());

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "getAllCircuitBreaker",fallbackMethod = "shoppingCartFallBack")
    ResponseEntity<List<ShoppingCartDto>> getAll(@PathVariable(name = "userId") String id);


    default ResponseEntity<List<?>> shoppingCartFallBack(String id, Exception e){
        return ResponseEntity.ok(List.of(id,e.getMessage()));
    }


}
