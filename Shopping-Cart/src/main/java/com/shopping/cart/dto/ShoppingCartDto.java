package com.shopping.cart.dto;

import com.shopping.cart.model.ShoppingCart;
import jakarta.persistence.Column;
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

    public ShoppingCartDto(ShoppingCart cart) {
        this.product = cart.getProduct();
        this.seller_id = cart.getSeller_id();
        this.userId=cart.getUserId();
    }
}
