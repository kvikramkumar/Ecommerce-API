package com.ecommerce.app.dto;

import com.ecommerce.app.model.Cart;
import com.ecommerce.app.model.CartItem;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDTO {

    private Long cartId;

    private Long userId;

    public List<CartItemDTO> getCartItemDTOList() {
        return cartItemDTOList;
    }

    public void setCartItemDTOList(List<CartItemDTO> cartItemDTOList) {
        this.cartItemDTOList = cartItemDTOList;
    }

    private List<CartItemDTO> cartItemDTOList;

    private Double totalPrice;

    public CartDTO(Long cartId, Long userId) {
        this.cartId = cartId;
        this.userId = userId;
    }

    public CartDTO() {
    }

    public Cart toModel(){
        return new Cart(this.getCartId(), this.getUserId());
    }

    public Long getCartId() {
        return cartId;
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

    public Double getTotalPrice() {
        return totalPrice;
    }
}
