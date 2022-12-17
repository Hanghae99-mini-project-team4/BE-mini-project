package com.mini.hanghae99miniproject.post.repository;

import com.mini.hanghae99miniproject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
