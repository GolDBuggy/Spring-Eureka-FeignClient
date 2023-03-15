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
                .seller_id(cartDto.getSeller_id()).user_id(cartDto.getUser_id()).build();
        cartRepository.save(shoppingCart);
        return cartDto;
    }


    public List<ShoppingCartDto> getProductsByUserId(String id){
        return cartRepository.getShoppingCartByUser_id(id).stream().map(
                c -> new ShoppingCartDto(c)
        ).collect(Collectors.toList());
    }
}
