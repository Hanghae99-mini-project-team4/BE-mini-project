package com.mini.hanghae99miniproject.comment.mapper;

import com.mini.hanghae99miniproject.comment.dto.RequestComment;
import com.mini.hanghae99miniproject.comment.dto.ResponseComment;
import com.mini.hanghae99miniproject.comment.entity.Comment;
import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.post.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    //받은 Comment를 받아서 dto로 만들어버린다. -> entity 바로 반환하는 것을 막기 위함.
    public ResponseComment toResponse(Comment comment){
        return ResponseComment.builder()
                .id(comment.getId())
                .userid(comment.getMember().getUserid())
                .content(comment.getContent())
                .nickname(comment.getMember().getNickname())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getLastModifiedAt())
                .build();
    }

    //받은 정보를 사용해서 Comment 만들어주기.
    public Comment toComment(Member member, Post post, RequestComment requestComment){
        return Comment.builder()
                .content(requestComment.getContent())
                .member(member)
                .post(post)
                .build();

    }


}
