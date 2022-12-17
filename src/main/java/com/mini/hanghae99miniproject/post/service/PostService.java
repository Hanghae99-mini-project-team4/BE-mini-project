package com.mini.hanghae99miniproject.post.service;

import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.post.dto.RequestPostDto;
import com.mini.hanghae99miniproject.post.dto.ResponsePostDto;
import com.mini.hanghae99miniproject.post.entity.Post;
import com.mini.hanghae99miniproject.post.mapper.PostMapper;
import com.mini.hanghae99miniproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Transactional
    public ResponsePostDto createPost(RequestPostDto requestPostDto, Member member) {

        Post post = postMapper.toEntity(requestPostDto, member);
        postRepository.save(post);

        return postMapper.postToResponsePostDto(post);
    }
}
