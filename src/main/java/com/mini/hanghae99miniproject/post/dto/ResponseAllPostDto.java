package com.mini.hanghae99miniproject.post.dto;

import com.mini.hanghae99miniproject.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseAllPostDto {

    //게시글 전체 조회에 사용될 DTO
    private Long id;
    private String title;
    private String content;
    private String userid;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public ResponseAllPostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userid = post.getMember().getUserid();
        this.nickname = post.getMember().getNickname();
        this.createdAt = post.getCreatedAt();
        this.lastModifiedAt = post.getLastModifiedAt();
    }
}
