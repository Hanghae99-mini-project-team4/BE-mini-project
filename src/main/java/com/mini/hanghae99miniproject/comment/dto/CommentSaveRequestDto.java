package com.mini.hanghae99miniproject.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {
    private String content;

    public CommentSaveDto toCommentDto() {
        return CommentSaveDto.builder()
                .content(content)
                .build();
    }
}
