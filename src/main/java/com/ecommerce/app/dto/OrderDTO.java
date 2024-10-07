package com.ecommerce.app.dto;

import com.ecommerce.app.model.Order;

public class OrderDTO {

    private Long userId;

    private Double totalAmount;

    private String orderStatus;

    private String shippingAddress;

    public OrderDTO(Long userId, Double totalAmount, String orderStatus, String shippingAddress) {
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.shippingAddress = shippingAddress;
    }

    public OrderDTO() {
    }

    public Order toModel(){
        Order order = new Order(this.userId,this.totalAmount,this.orderStatus,this.shippingAddress);
        return order;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
