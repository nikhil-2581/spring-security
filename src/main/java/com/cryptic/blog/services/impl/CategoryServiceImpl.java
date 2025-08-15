package com.cryptic.blog.services.impl;

import com.cryptic.blog.domain.entities.Category;
import com.cryptic.blog.repo.CategoryRepo;
import com.cryptic.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public List<Category> listCategories() {
        return categoryRepo.findAllWithPostCount();
    }
}
