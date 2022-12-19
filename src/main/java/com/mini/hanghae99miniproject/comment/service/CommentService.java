package com.mini.hanghae99miniproject.comment.service;

import com.mini.hanghae99miniproject.comment.dto.*;
import com.mini.hanghae99miniproject.comment.entity.Comment;
import com.mini.hanghae99miniproject.comment.mapper.CommentMapper;
import com.mini.hanghae99miniproject.comment.repository.CommentRepository;
import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.member.repository.MemberRepository;
import com.mini.hanghae99miniproject.post.entity.Post;
import com.mini.hanghae99miniproject.post.entity.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.mini.hanghae99miniproject.common.exception.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentMapper commentMapper;
    // 댓글 생성
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

    //댓글 생성 Version2
    public ResponseComment createComment2(RequestComment requestComment, Member member, Long postId){
        //게시글 존재 여부 확인
        Post post = postRepository.findById(postId).orElseThrow(
                new IllegalArgumentException(NO_EXIST_POSTING_ERROR_MSG.getMsg())
        );

        Comment comment = commentMapper.toComment(member, post, requestComment);
        commentRepository.save(comment);
        return commentMapper.toResponse(comment);
    }
    //댓글 수정
    public CommentUpdateResponseDto updateComment(CommentUpdateDto commentUpdateDto, Member member, Long commentId){
        //댓글 내용
        String content = commentUpdateDto.getContent();
        //댓글 존재 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_COMMENT_ERROR_MSG.getMsg())
        );
        //댓글을 작성한 사람과 유저의 정보가 일치하는지 확인.
        if(!comment.getMember().getId().equals(member.getId())){
            throw new IllegalArgumentException(USER_NOT_MATCH_ERROR_MSG.getMsg());
        }
        //댓글 내용 수정
        comment.update(content);
        return new CommentUpdateResponseDto(comment);
    }

    //댓글 수정 Version2
    public ResponseComment updateComment2(RequestComment requestComment, Member member, Long commentId){
        String content = requestComment.getContent();
        //게시글 존재 여부 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_COMMENT_ERROR_MSG.getMsg())
        );
        //댓글 작성자와 유저 정보가 일치하는지 확인.
        if(!comment.getMember().getId().equals(member.getId())){
            throw new IllegalArgumentException(USER_NOT_MATCH_ERROR_MSG.getMsg());
        }
        comment.update(content);
        return commentMapper.toResponse(comment);
    }

    //댓글 삭제
    public void deleteComment(Member member, Long commentId) {
        //댓글 존재 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_COMMENT_ERROR_MSG.getMsg())
        );
        //댓글을 작성한 사람과 유저의 정보가 일치하는지 확인.
        if(!comment.getMember().getId().equals(member.getId())){
            throw new IllegalArgumentException(USER_NOT_MATCH_ERROR_MSG.getMsg());
        }
        commentRepository.deleteById(commentId);
    }

    //댓글 삭제 Version2
    public void deleteComment2(Member member, Long commentId) {
        //댓글 존재 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_COMMENT_ERROR_MSG.getMsg())
        );
        //댓글 작성자와 유저 정보가 일치하는지 확인.
        if(!comment.getMember().getId().equals(member.getId())){
            throw new IllegalArgumentException(USER_NOT_MATCH_ERROR_MSG.getMsg());
        }
        commentRepository.deleteById(commentId);
    }
}
