package com.cryptic.blog.mapper;

import com.cryptic.blog.domain.DTO.TagResponse;
import com.cryptic.blog.domain.PostStatus;
import com.cryptic.blog.domain.entities.Post;
import com.cryptic.blog.domain.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tag tag);

    @Named("calculatePostCount")
     default Integer calculatePostCount(Set<Post> posts){
          if(posts.isEmpty()){
              return 0;
          }

          return (int)posts.stream()
                  .filter( post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                  .count();
     }
}
