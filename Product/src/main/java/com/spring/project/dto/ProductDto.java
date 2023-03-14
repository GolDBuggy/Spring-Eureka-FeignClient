package com.spring.project.dto;

import com.spring.project.model.Product;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {


    private String name;
    private BigDecimal price;
    private int count;
    private String description;
    private String seller_id;


    public ProductDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.count = product.getCount();
        this.description = product.getDescription();
        this.seller_id = product.getSeller_id();
    }
}
