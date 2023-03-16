package com.shopping.cart.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ShoppingCart {

    @Id
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "UUID")
    @Column(name = "shopping_cart_id")
    private String id;


    @Column(name = "product_name")
    private String product;


    @Column(name = "seller_id")
    private String seller_id;


    @Column(name = "user_id")
    private String userId;


}
