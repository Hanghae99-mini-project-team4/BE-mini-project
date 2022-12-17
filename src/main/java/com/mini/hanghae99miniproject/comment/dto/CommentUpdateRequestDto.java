package com.mini.hanghae99miniproject.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {
    private String content;

    public CommentUpdateDto toCommentDto() {
        return CommentUpdateDto.builder()
                .content(content)
                .build();
    }
}
