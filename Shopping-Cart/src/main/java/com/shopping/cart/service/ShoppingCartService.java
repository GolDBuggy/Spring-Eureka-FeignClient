package com.shopping.cart.service;

import com.shopping.cart.dto.ShoppingCartDto;
import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository cartRepository;

    public ShoppingCartService(ShoppingCartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public ShoppingCartDto save(ShoppingCartDto cartDto){
        ShoppingCart shoppingCart=ShoppingCart.builder().product(cartDto.getProduct())
                .seller_id(cartDto.getSeller_id()).userId(cartDto.getUserId()).build();
        cartRepository.save(shoppingCart);
        return cartDto;
    }


    public List<ShoppingCartDto> getProductsByUserId(String id){
        List<ShoppingCart> shoppingCarts=cartRepository.findShoppingCartsByUserId(id);
        if(shoppingCarts.isEmpty())
            throw new RuntimeException("Empty!");

        return cartRepository.findShoppingCartsByUserId(id).
                stream().
                map(e -> new ShoppingCartDto(e)).collect(Collectors.toList());
    }
}
