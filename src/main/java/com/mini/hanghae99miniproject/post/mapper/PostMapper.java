package com.mini.hanghae99miniproject.post.mapper;

import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.post.dto.RequestPostDto;
import com.mini.hanghae99miniproject.post.dto.ResponsePostDto;
import com.mini.hanghae99miniproject.post.entity.Post;
import org.springframework.stereotype.Component;

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

    // Entity -> Dto
    public ResponsePostDto postToResponsePostDto(Post post) {
        ResponsePostDto responsePostDto = new ResponsePostDto();

        responsePostDto.setId(post.getId());
        responsePostDto.setTitle(post.getTitle());
        responsePostDto.setContent(post.getContent());
        responsePostDto.setUserid(post.getMember().getUserid());
        responsePostDto.setNickname(post.getMember().getNickname());
        responsePostDto.setCreatedAt(post.getCreatedAt());
        responsePostDto.setLastModifiedAt(post.getLastModifiedAt());

        return responsePostDto;
    }

}
