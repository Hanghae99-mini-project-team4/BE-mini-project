package com.mini.hanghae99miniproject.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateDto {
    private String content;

    @Builder
    public CommentUpdateDto(String content) {
        this.content = content;
    }
}
