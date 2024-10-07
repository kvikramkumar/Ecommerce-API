package com.ecommerce.app.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long cartId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Cart(Long cartId, Long userId) {
        this.cartId = cartId;
        this.userId = userId;
    }

    public Cart() {

    }

    public Long getCartId() {
        return cartId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
