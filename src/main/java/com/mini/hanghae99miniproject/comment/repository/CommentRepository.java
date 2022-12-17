package com.mini.hanghae99miniproject.comment.repository;

import com.mini.hanghae99miniproject.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
