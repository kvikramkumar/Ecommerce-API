package com.ecommerce.app.controller;

import com.ecommerce.app.dto.ProductDTO;
import com.ecommerce.app.model.Product;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

   @PostMapping("/api/products")
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO){
       return productService.createProduct(productDTO);
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductDTO>> getProducts(){
       return productService.getProducts();
    }

    @GetMapping("/api/products/categories/{categoryID}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable("categoryID") Long categoryID){
       return productService.getProductsByCategory(categoryID);
    }

    @GetMapping("/api/products/{productID}")
    public ResponseEntity<?> getProduct(@PathVariable("productID") Long productID){

       return productService.getProduct(productID);
    }

    @PutMapping("/api/products")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO){
       return productService.updateProduct(productDTO);
    }

    @DeleteMapping("/api/products/{productID}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productID") Long productId){
       return productService.deleteProduct(productId);
    }
}
