package com.ecommerce.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long categoryID;

    @NotBlank(message = "Category Name is mandatory")
    @Column(name = "name")
    private String categoryName;

    @NotBlank(message = "Category Description is mandatory")
    @Column(name = "description")
    private String description;

    @Column(name = "created_at",insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public Category() {
    }

    public Category(Long id, String name, String description) {
        this.categoryID = id;
        this.categoryName = name;
        this.description = description;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
