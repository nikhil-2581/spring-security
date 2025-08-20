package com.cryptic.blog.services.impl;

import com.cryptic.blog.domain.entities.Tag;
import com.cryptic.blog.repo.TagRepo;
import com.cryptic.blog.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepo tagRepo;

    @Override
    public List<Tag> getTags() {
        return tagRepo.getAllTagsWithPostCount();
    }

    @Transactional
    @Override
    public List<Tag> createTags(Set<String> tagNames) {
        List<Tag> exisitingTags = tagRepo.findByNameIn(tagNames);

        Set<String> existingTagNames = exisitingTags.stream()
                .map(tag -> tag.getName())
                .collect(Collectors.toSet());

        List<Tag> newTags = tagNames.stream()
                .filter(name -> !existingTagNames.contains(name))
                .map(name -> Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build())
                .collect(Collectors.toList());

        List<Tag> savedTags = new ArrayList<>();

        if(!newTags.isEmpty()){
            savedTags = tagRepo.saveAll(newTags);
        }

        savedTags.addAll(exisitingTags);

        return savedTags;
    }

    @Transactional
    @Override
    public void deleteTag(UUID id) {
        tagRepo.findById(id).ifPresent( tag -> {
            if(!tag.getPosts().isEmpty()){
                throw new IllegalStateException("cannot delete tag associated with posts");
            }
            tagRepo.deleteById(id);
        });
    }

    @Override
    public Tag findTagById(UUID id) {
        return tagRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Tag not found with id: "+id));
    }
}
