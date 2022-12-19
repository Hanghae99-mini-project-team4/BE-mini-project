package com.mini.hanghae99miniproject.post.repository;

import com.mini.hanghae99miniproject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndMemberId(Long id, Long memberId);
}
