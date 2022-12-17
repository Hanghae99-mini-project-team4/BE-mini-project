package com.mini.hanghae99miniproject.comment.service;

import com.mini.hanghae99miniproject.comment.dto.CommentSaveDto;
import com.mini.hanghae99miniproject.comment.entity.Comment;
import com.mini.hanghae99miniproject.comment.repository.CommentRepository;
import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.member.repository.MemberRepository;
import com.mini.hanghae99miniproject.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.mini.hanghae99miniproject.common.exception.ExceptionMessage.NO_EXIST_POSTING_ERROR_MSG;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    public void createComment(CommentSaveDto commentSaveDto, Member member, Long postId){
        //댓글 내용
        String content = commentSaveDto.getContent();

        //게시글 존재 여부 확인
        Post post = postRepository.findById(postId).orElseThrow(
                new IllegalArgumentException(NO_EXIST_POSTING_ERROR_MSG.getMsg())
        );
        Comment comment = new Comment(content, member, post);
        commentRepository.save(comment);
    }
}
