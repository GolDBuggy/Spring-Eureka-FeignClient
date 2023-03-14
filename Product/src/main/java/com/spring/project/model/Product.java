package com.spring.project.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "UUID")
    @Column(name = "product_id")
    private String id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private BigDecimal price;
    @Column(name = "product_count")
    private int count;
    @Column(name = "product_description")
    private String description;
    @Column(name = "seller_id")
    private String seller_id;


}
