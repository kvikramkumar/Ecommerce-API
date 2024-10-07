package com.ecommerce.app.service;

import com.ecommerce.app.dto.ProductDTO;
import com.ecommerce.app.model.Product;
import com.ecommerce.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private ProductDTO productDTO;


    public ResponseEntity<String> createProduct(ProductDTO productDTO) {
        productRepository.save(productDTO.toModel());
        return ResponseEntity.status(201).body("Product is created");
    }

    public ResponseEntity<List<ProductDTO>> getProducts() {

        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();

        for(Product product : productList){
            productDTO = new ProductDTO(product);
            productDTOList.add(productDTO);
        }

        return ResponseEntity.status(200).body(productDTOList);
    }

    public ResponseEntity<List<ProductDTO>> getProductsByCategory(Long categoryID) {

        List<Product> productList = productRepository.findAllByCategoryID(categoryID);

        List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();

        for(Product product : productList){
            productDTO = new ProductDTO(product);
            productDTOList.add(productDTO);
        }

        return ResponseEntity.status(200).body(productDTOList);
    }

    public ResponseEntity<?> getProduct(Long productID) {

        Product product = productRepository.findById(productID).orElse(null);
        if(product == null)
            return ResponseEntity.status(404).body("Product not found");
        else {
            productDTO = new ProductDTO(product);
            return ResponseEntity.status(200).body(productDTO);
        }
    }

    public ResponseEntity<String> updateProduct(ProductDTO productDTO) {

        Product product = productRepository.findById(productDTO.getProductID()).orElse(null);
        if(product == null)
            return ResponseEntity.status(404).body("Product Not Found");

        if (productDTO.getProductName() != null) {
            product.setProductName(productDTO.getProductName());
        }
        if (productDTO.getDescription() != null) {
            product.setDescription(productDTO.getDescription());
        }
        if (productDTO.getPrice() != null) {
            product.setPrice(productDTO.getPrice());
        }
        if (productDTO.getStockQuantity() != null) {
            product.setStockQuantity(productDTO.getStockQuantity());
        }
        if (productDTO.getCategoryID() != null) {
            product.setCategoryID(productDTO.getCategoryID());
        }

        productRepository.save(product);
        return ResponseEntity.status(200).body("Product is Updated");

    }

    public ResponseEntity<String> deleteProduct(Long productId) {

        Product product = productRepository.findById(productId).orElse(null);
        if(product == null)
            return ResponseEntity.status(404).body("Product Not Found");
        else
        {
            productRepository.deleteById(productId);
            return ResponseEntity.status(200).body("Product is Deleted");
        }
    }
}
