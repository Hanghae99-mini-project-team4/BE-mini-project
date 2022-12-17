package com.mini.hanghae99miniproject.post.controller;

import com.mini.hanghae99miniproject.common.response.DataResponse;
import com.mini.hanghae99miniproject.post.dto.RequestPostDto;
import com.mini.hanghae99miniproject.post.dto.ResponsePostDto;
import com.mini.hanghae99miniproject.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mini.hanghae99miniproject.common.response.ResponseMessage.CREATE_POSTING_SUCCESS_MSG;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public DataResponse<ResponsePostDto> createPost(@RequestBody RequestPostDto requestPostDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ResponsePostDto response = postService.createPost(requestPostDto, userDetails.getMember());
        return new DataResponse<>(CREATE_POSTING_SUCCESS_MSG,response);
    }


}
