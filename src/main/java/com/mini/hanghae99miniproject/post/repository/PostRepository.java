package com.mini.hanghae99miniproject.post.repository;

import com.mini.hanghae99miniproject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByContentContainingIgnoreCaseOrTitleContainingIgnoreCase(String title, String content);
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findAllByOrderByRecommendCountDescCreatedAtDesc();
}
