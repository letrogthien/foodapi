package com.foodapi.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapi.demo.models.Cart;
import com.foodapi.demo.models.CartItem;
import com.foodapi.demo.models.Product;
import com.foodapi.demo.models.User;
import com.foodapi.demo.services.AuthenticationService;
import com.foodapi.demo.services.CartItemService;
import com.foodapi.demo.services.CartService;
import com.foodapi.demo.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/item/all")
    public ResponseEntity<?> getAllCartItem() {
        User user = authenticationService.authenticationUser();
        Cart cart = cartService.getByUser(user).orElseThrow();
        return new ResponseEntity<>(cartItemService.getAllCartItem(cart.getId()),HttpStatus.OK);
    }

    @PostMapping("/item/add")
    public ResponseEntity<?> addToCart(@RequestParam Integer productId,@RequestParam Integer quantity) {
        User user = authenticationService.authenticationUser();
        Cart cart = cartService.getByUser(user).orElseThrow();
        Product product = productService.getProductById(productId).orElseThrow();
        cartItemService.createCartItem(cart, product, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/item/delete")
    public ResponseEntity<?> deleteCartItem(@RequestParam Integer cartItemId) {
        User user = authenticationService.authenticationUser();
        Cart cart = cartItemService.getById(cartItemId).orElseThrow().getCart();
        Cart cart2 = cartService.getByUser(user).orElseThrow();
        if (cart.getId() != cart2.getId()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cartItemService.deleteCartItem(cartItemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("/item/delete/all")
    public ResponseEntity<?> deleteCartItemOfCart() {
        User user = authenticationService.authenticationUser();
        Cart cart = cartService.getByUser(user).orElseThrow();
        cartItemService.deleteAllCartItem(cart.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    
}
