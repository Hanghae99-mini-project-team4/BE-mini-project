package com.mini.hanghae99miniproject.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestPostDto {

    // 게시글 등록 및 수정에 사용될 DTO
    private String title;
    private String content;

}
