package com.mini.hanghae99miniproject.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveDto {
    private String content;

    @Builder
    public CommentSaveDto(String content){
        this.content = content;
    }
}
