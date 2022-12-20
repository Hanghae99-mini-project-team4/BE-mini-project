package com.mini.hanghae99miniproject.comment.service;

import com.mini.hanghae99miniproject.comment.entity.Comment;
import com.mini.hanghae99miniproject.comment.mapper.CommentMapper;
import com.mini.hanghae99miniproject.comment.dto.RequestComment;
import com.mini.hanghae99miniproject.comment.dto.ResponseComment;
import com.mini.hanghae99miniproject.comment.repository.CommentRepository;
import com.mini.hanghae99miniproject.common.exception.ExceptionMessage;
import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.member.repository.MemberRepository;
import com.mini.hanghae99miniproject.post.entity.Post;
import com.mini.hanghae99miniproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentMapper commentMapper;

    //댓글 생성 Version2
    @Transactional
    public ResponseComment createComment(RequestComment requestComment, Member member, Long postId){
        //게시글 존재 여부 확인
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException(ExceptionMessage.NO_EXIST_POSTING_ERROR_MSG.getMsg())
        );
        Comment comment = commentMapper.toComment(member, post, requestComment);
        commentRepository.save(comment);
        return commentMapper.toResponse(comment);
    }
    //댓글 수정 Version2
    @Transactional
    public ResponseComment updateComment(RequestComment requestComment, Member member, Long commentId){
        String content = requestComment.getContent();
        //게시글 존재 여부 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException(ExceptionMessage.NO_EXIST_COMMENT_ERROR_MSG.getMsg())
        );
        //댓글 작성자와 유저 정보가 일치하는지 확인.
        if(!comment.getMember().getId().equals(member.getId())){
            throw new IllegalArgumentException(ExceptionMessage.USER_NOT_MATCH_ERROR_MSG.getMsg());
        }
        comment.update(content);
        return commentMapper.toResponse(comment);
    }

    //댓글 삭제 Version2
    @Transactional
    public void deleteComment(Member member, Long commentId) {
        //댓글 존재 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException(ExceptionMessage.NO_EXIST_COMMENT_ERROR_MSG.getMsg())
        );
        //댓글 작성자와 유저 정보가 일치하는지 확인.
        if(!comment.getMember().getId().equals(member.getId())){
            throw new IllegalArgumentException(ExceptionMessage.USER_NOT_MATCH_ERROR_MSG.getMsg());
        }
        commentRepository.deleteById(commentId);
    }

    //댓글 수정 전 유저 체크
    public void checkComment(Member member, Long commentId) {
        //댓글 존재 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException(ExceptionMessage.NO_EXIST_COMMENT_ERROR_MSG.getMsg())
        );
        //댓글 작성자와 유저 정보가 일치하는지 확인.
        if(!comment.getMember().getId().equals(member.getId())){
            throw new IllegalArgumentException(ExceptionMessage.USER_NOT_MATCH_ERROR_MSG.getMsg());
        }
    }
}
