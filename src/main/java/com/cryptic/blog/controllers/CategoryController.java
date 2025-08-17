package com.cryptic.blog.controllers;

import com.cryptic.blog.domain.DTO.CategoryDTO;
import com.cryptic.blog.domain.DTO.CreateCategoryRequest;
import com.cryptic.blog.domain.entities.Category;
import com.cryptic.blog.mapper.CategoryMapper;
import com.cryptic.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List< CategoryDTO>> listCategories(){
        List<CategoryDTO> categories = categoryService.listCategories()
                .stream()
                .map(category -> categoryMapper.toDTO(category))
                .toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
            Category categoryToCreate = categoryMapper.toEntity(createCategoryRequest);

            Category savedCategory = categoryService.createCategory(categoryToCreate);

            return new ResponseEntity<>(
                    categoryMapper.toDTO(savedCategory),
                    HttpStatus.CREATED
            );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
