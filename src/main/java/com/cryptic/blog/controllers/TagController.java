package com.cryptic.blog.controllers;

import com.cryptic.blog.domain.DTO.CreateTagRequest;
import com.cryptic.blog.domain.DTO.TagResponse;
import com.cryptic.blog.domain.entities.Tag;
import com.cryptic.blog.mapper.TagMapper;
import com.cryptic.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags(){
        List<Tag> tags = tagService.getTags();

        List<TagResponse> tagResponses = tags.stream()
                .map( tag -> tagMapper.toTagResponse(tag))
                .toList();

        return ResponseEntity.ok(tagResponses);
    }


    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagRequest createTagRequest){
        List<Tag> savedTags = tagService.createTags(createTagRequest.getNames());

        List<TagResponse> createdTagResponses = savedTags.stream()
                .map(tag -> tagMapper.toTagResponse(tag))
                .toList();

        return new ResponseEntity<>(
                createdTagResponses,
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTags(@PathVariable UUID id){
        tagService.deleteTag(id);

        return ResponseEntity.noContent().build();
    }
}
