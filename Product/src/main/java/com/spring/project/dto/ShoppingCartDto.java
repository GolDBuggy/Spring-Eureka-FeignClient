package com.spring.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoppingCartDto {

    private String product;


    private String seller_id;

    private String userId;


    public ShoppingCartDto(String product) {
        this.product = product;
    }
}
