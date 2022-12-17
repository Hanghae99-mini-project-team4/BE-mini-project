package com.mini.hanghae99miniproject.post.controller;

import com.mini.hanghae99miniproject.common.response.DataResponse;
import com.mini.hanghae99miniproject.common.response.Response;
import com.mini.hanghae99miniproject.post.dto.RequestPostDto;
import com.mini.hanghae99miniproject.post.dto.ResponseAllPostDto;
import com.mini.hanghae99miniproject.post.dto.ResponsePostDto;
import com.mini.hanghae99miniproject.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mini.hanghae99miniproject.common.response.ResponseMessage.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시글 등록
    @PostMapping("")
    public DataResponse<ResponsePostDto> createPost(@RequestBody RequestPostDto requestPostDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ResponsePostDto response = postService.createPost(requestPostDto, userDetails.getMember());
        return new DataResponse<>(CREATE_POSTING_SUCCESS_MSG,response);
    }

    //게시글 전체 조회
    @GetMapping("")
    public DataResponse<List<ResponseAllPostDto>> findAllPost() {
        List<ResponseAllPostDto> response = postService.findAllPost();
        return new DataResponse<>(READ_POSTING_SUCCESS_MSG, response);
    }

    //게시글 선택 조회
    @GetMapping("{id}")
    public DataResponse<ResponsePostDto> findOnePost(@PathVariable Long id) {
        ResponsePostDto response = postService.findOnePost(id);
        return new DataResponse<>(READ_POSTING_SUCCESS_MSG, response);
    }

    //게시글 수정
    @PutMapping("{id}")
    public DataResponse<ResponsePostDto> updatePost(@PathVariable Long id, @RequestBody RequestPostDto requestPostDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ResponsePostDto response = postService.updatePost(id, requestPostDto, userDetails.getMember());
        return new DataResponse<>(UPDATE_POSTING_SUCCESS_MSG, response);
    }

    //게시글 삭제
    @DeleteMapping("{id}")
    public Response deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(id, userDetails.getMember());
        return new Response(DELETE_POSTING_SUCCESS_MSG);
    }


}
