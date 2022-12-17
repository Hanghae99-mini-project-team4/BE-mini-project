package com.mini.hanghae99miniproject.comment.service;

import com.mini.hanghae99miniproject.comment.dto.CommentSaveDto;
import com.mini.hanghae99miniproject.comment.dto.CommentSaveResponseDto;
import com.mini.hanghae99miniproject.comment.dto.CommentUpdateDto;
import com.mini.hanghae99miniproject.comment.dto.CommentUpdateResponseDto;
import com.mini.hanghae99miniproject.comment.entity.Comment;
import com.mini.hanghae99miniproject.comment.repository.CommentRepository;
import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.member.repository.MemberRepository;
import com.mini.hanghae99miniproject.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.mini.hanghae99miniproject.common.exception.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    public CommentSaveResponseDto createComment(CommentSaveDto commentSaveDto, Member member, Long postId){
        //댓글 내용
        String content = commentSaveDto.getContent();
        //게시글 존재 여부 확인
        Post post = postRepository.findById(postId).orElseThrow(
                new IllegalArgumentException(NO_EXIST_POSTING_ERROR_MSG.getMsg())
        );
        //유저 정보 확인
        Member memberinfo = memberRepository.findById(member.getId()).orElseThrow(
                new IllegalArgumentException(USER_NOT_FOUND_ERROR_MSG.getMsg())
        );
        //댓글 저장
        Comment comment = new Comment(content, member, post);
        commentRepository.save(comment);
        return new CommentSaveResponseDto(comment);
    }

    public CommentUpdateResponseDto updateComment(CommentUpdateDto commentUpdateDto, Member member, Long commentId){
        //댓글 내용
        String content = commentUpdateDto.getContent();
        //댓글 존재 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_COMMENT_ERROR_MSG.getMsg())
        );
        //유저 정보 확인
        Member memberinfo = memberRepository.findById(member.getId()).orElseThrow(
                () -> new IllegalArgumentException(USER_NOT_FOUND_ERROR_MSG.getMsg())
        );
        //댓글 내용 수정
        comment.update(content);
        return new CommentUpdateResponseDto(comment);
    }

    public void deleteComment(Member member, Long commentId) {
    }
}
