package com.cryptic.blog.controllers;

import com.cryptic.blog.domain.DTO.CategoryDTO;
import com.cryptic.blog.domain.entities.Category;
import com.cryptic.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List< CategoryDTO>> listCategories(){
        List<Category> categories = categoryService.listCategories();
        return categories;
    }
}
