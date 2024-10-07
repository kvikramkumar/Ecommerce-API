package com.ecommerce.app.service;

import com.ecommerce.app.dto.CartDTO;
import com.ecommerce.app.dto.CartItemDTO;
import com.ecommerce.app.model.Cart;
import com.ecommerce.app.model.CartItem;
import com.ecommerce.app.model.Product;
import com.ecommerce.app.repository.CartItemRepository;
import com.ecommerce.app.repository.CartRepository;
import com.ecommerce.app.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<String> addToCart(Long userId, CartItemDTO cartItem) {

       Cart cart = cartRepository.findByUserId(userId);
       if(cart == null)
       {
           cart = new Cart();
           cart.setUserId(userId);
           cartRepository.save(cart);

       }

       CartItemDTO cartItemDTO = new CartItemDTO();
       cartItemDTO.setCartId(cart.getCartId());
       cartItemDTO.setProductId(cartItem.getProductId());
       cartItemDTO.setQuantity(cartItem.getQuantity());

       try {
           cartItemRepository.save(cartItemDTO.toModel());
           return ResponseEntity.status(201).body("Item Added to Cart");
       }
       catch (Exception exception){

           return ResponseEntity.status(404).body("Product Not Found");
       }

    }

    public ResponseEntity<CartDTO> getCartItems(Long userId) {

        Cart cart = cartRepository.findByUserId(userId);

        List<CartItem> cartItemList = cartItemRepository.findAllByCartId(cart.getCartId());

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setUserId(cart.getUserId());

        List<CartItemDTO> cartItemDTOList = new ArrayList<>();

        for(CartItem cartItem : cartItemList){
           Optional<Product> product = productRepository.findById(cartItem.getProductId());
           if(product.isPresent()){

               CartItemDTO cartItemDTO = new CartItemDTO();
               cartItemDTO.setProductId(product.get().getProductID());
               cartItemDTO.setProductName(product.get().getProductName());
               cartItemDTO.setDescription(product.get().getDescription());
               cartItemDTO.setQuantity(cartItem.getQuantity());
               cartItemDTOList.add(cartItemDTO);
           }
        }
        cartDTO.setCartItemDTOList(cartItemDTOList);
        return ResponseEntity.status(200).body(cartDTO);
    }

    public ResponseEntity<String> updateProductQuantity(Long userId, Long productId, CartItemDTO cartItemDTO) {

        Cart cart = cartRepository.findByUserId(userId);
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getCartId(),productId);

        if(cartItem != null)
        {
            cartItem.setQuantity(cartItemDTO.getQuantity());
            cartItemRepository.save(cartItem);
            return ResponseEntity.status(200).body("Product Quantity is updated");
        }
        return ResponseEntity.status(404).body("Product is not found");
    }

    @Transactional
    public ResponseEntity<String> deleteProductFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId);
        cartItemRepository.deleteByCartIdAndProductId(cart.getCartId(),productId);
        return ResponseEntity.status(200).body("Product removed from cart");

    }

    @Transactional
    public ResponseEntity<String> deleteCartItems(Long userId) {

        Cart cart = cartRepository.findByUserId(userId);
        cartItemRepository.deleteByCartId(cart.getCartId());
        return ResponseEntity.status(200).body("All cart items removed");
    }
}
