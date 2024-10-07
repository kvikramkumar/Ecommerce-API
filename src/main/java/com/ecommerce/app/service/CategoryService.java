package com.ecommerce.app.service;

import com.ecommerce.app.dto.CategoryDTO;
import com.ecommerce.app.model.Category;
import com.ecommerce.app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private CategoryDTO categoryDTO;
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
        for(Category c : categoryList){
            categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryID(c.getCategoryID());
            categoryDTO.setCategoryName(c.getCategoryName());
            categoryDTO.setDescription(c.getDescription());
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    public ResponseEntity<?> getCategory(Long categoryID) {

        Category category = categoryRepository.findById(categoryID).orElse(null);
        if(category == null){
            return ResponseEntity.status(404).body("Category not found");
        }
        else{
            categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryID(category.getCategoryID());
            categoryDTO.setCategoryName(category.getCategoryName());
            categoryDTO.setDescription(category.getDescription());

            return ResponseEntity.status(200).body(categoryDTO);
        }

    }

    public boolean updateCategoryDetails(Category category) {

        Category existingCategory = categoryRepository.findById(category.getCategoryID()).orElse(null);
        if(existingCategory == null)
            return false;
        categoryRepository.save(category);
        return true;
    }


    public boolean deleteCategory(Long categoryID) {
        Category category = categoryRepository.findById(categoryID).orElse(null);
        if(category != null)
        {
            categoryRepository.deleteById(categoryID);
            return true;
        }
        else {
            return false;
        }
    }
}
