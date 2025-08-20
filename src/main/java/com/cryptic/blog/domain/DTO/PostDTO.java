package com.cryptic.blog.domain.DTO;

import com.cryptic.blog.domain.PostStatus;
import com.cryptic.blog.services.AuthenticationService;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class PostDTO  {

    private UUID id;
    private String title;
    private String content;
    private AuthorDTO author;
    private CategoryDTO category;
    private Set<TagResponse> tags;
    private Integer readingTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private PostStatus postStatus;

}
