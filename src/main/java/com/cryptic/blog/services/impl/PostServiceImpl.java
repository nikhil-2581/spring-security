package com.cryptic.blog.services.impl;

import com.cryptic.blog.domain.PostStatus;
import com.cryptic.blog.domain.entities.Category;
import com.cryptic.blog.domain.entities.Post;
import com.cryptic.blog.domain.entities.Tag;
import com.cryptic.blog.repo.PostRepo;
import com.cryptic.blog.services.CategoryService;
import com.cryptic.blog.services.PostService;
import com.cryptic.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

     private final PostRepo postRepo;
     private final TagService tagService;
     private final CategoryService categoryService;


    @Override
    @Transactional(readOnly = true )
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {
        if(categoryId!=null && tagId!=null) {
            Category category = categoryService.findById(categoryId);
            Tag tag = tagService.findTagById(tagId);
            return postRepo.findAllByStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED,
                    category,
                    tag
            );
        }

        if(categoryId!=null){
            Category category = categoryService.findById(categoryId);
            return  postRepo.findAllByStatusAndCategory(
                    PostStatus.PUBLISHED,
                    category
            );
        }

        if(tagId!=null){
            Tag tag = tagService.findTagById(tagId);
            return postRepo.findAllByStatusAndTagsContaining(
                    PostStatus.PUBLISHED,
                    tag
            );
        }

        return postRepo.findAllByStatus(PostStatus.PUBLISHED);
    }
}
