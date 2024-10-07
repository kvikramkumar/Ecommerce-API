package com.ecommerce.app.controller;

import com.ecommerce.app.dto.CartDTO;
import com.ecommerce.app.dto.CartItemDTO;
import com.ecommerce.app.model.CartItem;
import com.ecommerce.app.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/api/carts/{userId}/items")
    public ResponseEntity<String> addToCart(@PathVariable("userId") Long userId, @RequestBody CartItemDTO cartItemDTO){
        return cartService.addToCart(userId,cartItemDTO);
    }

    @GetMapping("/api/carts/{userId}")
    public ResponseEntity<CartDTO> getCartItems(@PathVariable("userId") Long userId){
        return cartService.getCartItems(userId);
    }

    @PutMapping("/api/carts/{userId}/items/{productId}")
    public ResponseEntity<String> updateProductQuantity(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId,@RequestBody CartItemDTO cartItemDTO){

        return cartService.updateProductQuantity(userId,productId,cartItemDTO);
    }


    @DeleteMapping("/api/carts/{userId}/items/{productId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable("userId") Long userId,@PathVariable("productId") Long productId){
        return cartService.deleteProductFromCart(userId,productId);
    }

    @DeleteMapping("/api/carts/{userId}")
    public ResponseEntity<String> deleteCartItems(@PathVariable("userId") Long userId){
        return cartService.deleteCartItems(userId);
    }
}
