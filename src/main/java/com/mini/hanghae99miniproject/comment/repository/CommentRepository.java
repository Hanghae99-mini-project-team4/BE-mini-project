package com.mini.hanghae99miniproject.comment.repository;

import com.mini.hanghae99miniproject.comment.entity.Comment;
import com.mini.hanghae99miniproject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteCommentByPost(Post post);

    //All로 했을 때 관련 없는 게시글이 지워지는지 안지워지는지 테스트
    void deleteAllByPost(Post post);
}
