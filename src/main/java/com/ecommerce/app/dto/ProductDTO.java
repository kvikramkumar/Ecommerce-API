package com.ecommerce.app.dto;

import com.ecommerce.app.model.Product;

public class ProductDTO {

    private Long productID;

    private String productName;

    private String description;

    private Double price;

    private Integer stockQuantity;

    private Long categoryID;

    public ProductDTO() {
    }

    public ProductDTO(Product product){
        this.productID = product.getProductID();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stockQuantity = product.getStockQuantity();
        this.categoryID = product.getCategoryID();
    }


    public Product toModel(){
        Product product = new Product();
        product.setProductName(this.getProductName());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setStockQuantity(this.getStockQuantity());
        product.setCategoryID(this.getCategoryID());
        return product;
    }
    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }
}
