package com.shopping.cart.repository;

import com.shopping.cart.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,String> {

    @Query("select u from ShoppingCart u where u.user_id=:id")
    List<ShoppingCart> getShoppingCartByUser_id(@Param("id") String id);
}
