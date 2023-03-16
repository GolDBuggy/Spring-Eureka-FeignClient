package com.shopping.cart.repository;

import com.shopping.cart.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,String> {


    List<ShoppingCart> findShoppingCartsByUserId(String id);
}
