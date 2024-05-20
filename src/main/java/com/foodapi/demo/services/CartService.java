package com.foodapi.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Cart;
import com.foodapi.demo.models.User;
import com.foodapi.demo.repositories.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public void createCart(Cart cart){
        cartRepository.save(cart);
    } 
    public Optional<Cart> getById(Integer cartId){
        return cartRepository.findById(cartId);
    }
    public Optional<Cart> getByUser(User user){
        return cartRepository.findByUser(user);
    }
   

}
