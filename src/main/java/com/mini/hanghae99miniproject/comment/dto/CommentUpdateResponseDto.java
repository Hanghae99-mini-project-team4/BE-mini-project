package com.mini.hanghae99miniproject.comment.dto;

import com.mini.hanghae99miniproject.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentUpdateResponseDto {
    private Long id;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentUpdateResponseDto(Comment comment) {
        this.id = comment.getMember().getId();
        this.content = comment.getContent();
        this.nickname = comment.getMember().getNickname();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getLastModifiedAt();
    }


}
