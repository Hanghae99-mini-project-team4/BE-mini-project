package com.mini.hanghae99miniproject.post.dto;

import com.mini.hanghae99miniproject.comment.entity.Comment;
import com.mini.hanghae99miniproject.post.entity.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Builder
public class ResponsePostDto {

    //게시글 선택 조회에 사용될 DTO
    private Long id;
    private String title;
    private String content;
    private String userid;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

//    private List<CommentSaveResponseDto> commentList = new ArrayList<>();

//    @Builder
//    public ResponsePostDto (Long id, String title) {
//        this.id = post.getId();
//        this.title = post.getTitle();
//        this.content = post.getContent();
//        this.userid = post.getMember().getUserid();
//        this.nickname = post.getMember().getNickname();
//        this.createdAt = post.getCreatedAt();
//        this.lastModifiedAt = post.getLastModifiedAt();
////        this.commentList = post.getCommentList();
////    }

}
