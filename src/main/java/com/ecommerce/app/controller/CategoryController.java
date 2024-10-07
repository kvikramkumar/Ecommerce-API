package com.ecommerce.app.controller;

import com.ecommerce.app.dto.CategoryDTO;
import com.ecommerce.app.model.Category;
import com.ecommerce.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/api/categories", method = RequestMethod.POST)
    public ResponseEntity<String> createCategory(@RequestBody Category category){

        try{
            categoryService.createCategory(category);
            return ResponseEntity.status(201).body("Category is created");
        }catch (Exception e) {
            return new ResponseEntity<>("Error creating user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/categories", method = RequestMethod.GET)
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "/api/categories/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getCategory(@PathVariable("id") Long categoryID){
        return categoryService.getCategory(categoryID);
    }

    @RequestMapping(value = "/api/categories", method = RequestMethod.PUT)
    public ResponseEntity<String> updateCategoryDetails(@RequestBody Category category){

        boolean isUpdated = categoryService.updateCategoryDetails(category);

        if(isUpdated)
            return ResponseEntity.status(204).body("Category is updated");
        else
            return ResponseEntity.status(404).body("Category Not Found");
    }

    @RequestMapping(value = "/api/categories/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryID){

        boolean isDeleted = categoryService.deleteCategory(categoryID);

        if(isDeleted)
            return ResponseEntity.status(200).body("Category is deleted");
        else
            return ResponseEntity.status(404).body("Category not found");
    }
}
