package com.mini.hanghae99miniproject.post.dto;

import com.mini.hanghae99miniproject.comment.entity.Comment;
import com.mini.hanghae99miniproject.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class ResponsePostDto {

    //게시글 선택 조회에 사용될 DTO
    private Long id;
    private String title;
    private String content;
    private String userid;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    //순환 참조 막기 위해 List 제네릭 자료형을 Entity 대신 DTO로 변경
    //추후에 변경
//    private List<CommentSaveResponseDto> comments = new ArrayList<>();

    public ResponsePostDto (Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userid = post.getMember().getUserid();
        this.nickname = post.getMember().getNickname();
        this.createdAt = post.getCreatedAt();
        this.lastModifiedAt = post.getLastModifiedAt();
//        for (Comment comment:post.getCommentList()) {
//            this.comments.add(new CommentSaveResponseDto(comment));
//        }
    }

}
