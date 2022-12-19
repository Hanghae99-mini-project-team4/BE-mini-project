package com.mini.hanghae99miniproject.post.mapper;

import com.mini.hanghae99miniproject.comment.dto.ResponseComment;
import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.post.dto.RequestPostDto;
import com.mini.hanghae99miniproject.post.dto.ResponsePostDto;
import com.mini.hanghae99miniproject.post.entity.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostMapper {

    // Dto -> Entity
    public Post toEntity(RequestPostDto requestDto, Member member) {
        return Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .member(member)
                .build();
    }
    //Entity -> Dto 게시글 전체 조회에 사용
    public ResponsePostDto postToResponsePostDtoALL(Post post) {
        return ResponsePostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .userid(post.getMember().getUserid())
                .nickname(post.getMember().getNickname())
                .createdAt(post.getCreatedAt())
                .lastModifiedAt(post.getLastModifiedAt())
                .build();
    }
    // Entity -> Dto
    public ResponsePostDto postToResponsePostDto(Post post, List<ResponseComment> commentList) {
        return ResponsePostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .userid(post.getMember().getUserid())
                .nickname(post.getMember().getNickname())
                .createdAt(post.getCreatedAt())
                .lastModifiedAt(post.getLastModifiedAt())
                .commentList(commentList)
                .build();
    }

}
