package com.cryptic.blog.controllers;

import com.cryptic.blog.domain.DTO.PostDTO;
import com.cryptic.blog.domain.entities.Post;
import com.cryptic.blog.mapper.PostMapper;
import com.cryptic.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(
            @RequestParam (required = false)UUID categoryId,
            @RequestParam (required = false)UUID tagId
            ){

        List<Post> posts = postService.getAllPosts(categoryId, tagId);

        List<PostDTO> postDTOs = posts.stream().map(post -> postMapper.toPostDTO(post)).toList();

        return ResponseEntity.ok(postDTOs);
    }


}

