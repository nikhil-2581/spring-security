package com.cryptic.blog.repo;

import com.cryptic.blog.domain.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagRepo extends JpaRepository<Tag, UUID> {
}
