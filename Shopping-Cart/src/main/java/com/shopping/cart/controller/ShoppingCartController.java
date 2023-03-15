package com.shopping.cart.controller;

import com.shopping.cart.dto.ShoppingCartDto;
import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.service.ShoppingCartService;
import feign.Response;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping/cart")
public class ShoppingCartController {

    private final ShoppingCartService cartService;

    public ShoppingCartController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/save")
    public ResponseEntity<ShoppingCartDto> save(@RequestBody ShoppingCartDto dto){
        return ResponseEntity.ok(cartService.save(dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ShoppingCartDto>> getAll(@PathVariable(name = "userId") String id){
        return ResponseEntity.ok(cartService.getProductsByUserId(id));
    }
}
