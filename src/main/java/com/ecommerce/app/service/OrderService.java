package com.ecommerce.app.service;

import com.ecommerce.app.dto.OrderDTO;
import com.ecommerce.app.model.CartItem;
import com.ecommerce.app.model.Order;
import com.ecommerce.app.model.Product;
import com.ecommerce.app.repository.CartItemRepository;
import com.ecommerce.app.repository.CartRepository;
import com.ecommerce.app.repository.OrderRepository;
import com.ecommerce.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ecommerce.app.model.Cart;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<String> placeOrder(OrderDTO orderDTO) {

        Cart cart = cartRepository.findByUserId(orderDTO.getUserId());

        List<CartItem> cartItemList = cartItemRepository.findAllByCartId(cart.getCartId());

        Double totalAmount = 0.0;

        if(cartItemList.size() == 0)
            return ResponseEntity.status(404).body("Cart is empty");

        for(CartItem cartItem : cartItemList){
            Optional<Product> product = productRepository.findById(cartItem.getProductId());
            totalAmount += product.get().getPrice() * cartItem.getQuantity();
            System.out.println(product.get().getPrice());
        }

        orderDTO.setOrderStatus("pending");
        orderDTO.setTotalAmount(totalAmount);

        Order order = orderDTO.toModel();

        orderRepository.save(order);

        return ResponseEntity.status(201).body("Order Placed");
    }
}
