package com.spring.project.service;

import com.spring.project.dto.ProductDto;
import com.spring.project.model.Product;
import com.spring.project.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ProductDto save(ProductDto productDto){
        Product product=Product.builder().
                name(productDto.getName()).description(productDto.getDescription()).
                price(productDto.getPrice()).count(productDto.getCount()).seller_id(productDto.getSeller_id()).
                build();
        productRepo.save(product);
        return productDto;
    }

    public List<ProductDto> getAll(){
        return productRepo.findAll().stream().map(p -> new ProductDto(p)).collect(Collectors.toList());
    }
}
