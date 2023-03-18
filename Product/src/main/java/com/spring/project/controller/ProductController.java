package com.spring.project.controller;

import com.spring.project.dto.ProductDto;
import com.spring.project.dto.ShoppingCartDto;
import com.spring.project.model.Product;
import com.spring.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.save(productDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAll(Principal principal){
        System.out.println(principal.getName());
        return ResponseEntity.ok(productService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<ShoppingCartDto>> getUserShoppingCart(@PathVariable String id){
        return ResponseEntity.ok(productService.getUserSC(id));
    }
}
