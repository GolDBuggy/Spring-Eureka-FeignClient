package com.spring.project.repository;

import com.spring.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,String> {

    @Query("select u from Product  u where u.seller_id=:id")
    List<Product> findProductBySeller_id(@Param("id") String id);
}
