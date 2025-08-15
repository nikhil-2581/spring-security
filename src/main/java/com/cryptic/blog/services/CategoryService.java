package com.cryptic.blog.services;

import com.cryptic.blog.domain.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listCategories();
}
