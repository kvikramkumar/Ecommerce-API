package com.ecommerce.app.repository;

import com.ecommerce.app.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findAllByCartId(Long cartId);

    CartItem findByCartIdAndProductId(Long cartId, Long productId);

    void deleteByCartIdAndProductId(Long cartId,Long productId);

    void deleteByCartId(Long cartId);
}
