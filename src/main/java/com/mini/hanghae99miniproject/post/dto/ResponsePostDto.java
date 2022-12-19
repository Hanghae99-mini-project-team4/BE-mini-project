package com.mini.hanghae99miniproject.post.dto;


import com.mini.hanghae99miniproject.comment.dto.ResponseComment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<ResponseComment> commentList;

}
