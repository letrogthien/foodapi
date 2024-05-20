package com.foodapi.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.demo.models.Cart;
import com.foodapi.demo.models.CartItem;
import com.foodapi.demo.models.Product;
import com.foodapi.demo.models.DTO.CartItemDto;
import com.foodapi.demo.models.DTO.ProductDto;
import com.foodapi.demo.repositories.CartItemRepository;
import com.foodapi.demo.repositories.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    public void createCartItem(Cart cart, Product product, Integer quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }
    public Optional<CartItem> getById(Integer id){
        
        return cartItemRepository.findById(id);
    }


    public void deleteCartItem(Integer cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
    @Transactional
    public void deleteAllCartItem(Integer id){
        cartItemRepository.deleteByCartId( id);
    }

    public void changeQuantity(Integer id, Integer newQuantity) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow();
        cartItem.setQuantity(newQuantity);
    }

    public void removeAllCartItem(Integer cartId) {
        cartItemRepository.deleteByCartId(cartId);
    }

    public List<CartItemDto> getAllCartItem(Integer cartId) {
        return cartItemRepository.findByCartId(cartId).stream().map(
                item -> new CartItemDto(item.getId(),
                        new ProductDto(item.getProduct().getId(), item.getProduct().getName(),
                                item.getProduct().getDescription(), item.getProduct().getImg(),
                                item.getProduct().getPrice(), item.getProduct().getCategory().getId(),
                                item.getProduct().getShop().getId()),
                        item.getQuantity()))
                .collect(Collectors.toList());
    }

}
