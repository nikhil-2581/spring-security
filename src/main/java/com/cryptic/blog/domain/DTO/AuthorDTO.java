package com.cryptic.blog.domain.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
public class AuthorDTO  {

    private UUID id;
    private String name;


}
