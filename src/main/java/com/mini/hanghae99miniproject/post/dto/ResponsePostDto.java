package com.mini.hanghae99miniproject.post.dto;


import lombok.Builder;

import com.mini.hanghae99miniproject.post.entity.Post;

import lombok.Getter;

import java.time.LocalDateTime;

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
//    private List<ResponseComment> commentList = new ArrayList<>();

}
