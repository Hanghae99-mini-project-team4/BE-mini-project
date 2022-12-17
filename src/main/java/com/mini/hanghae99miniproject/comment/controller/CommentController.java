package com.mini.hanghae99miniproject.comment.controller;

import com.mini.hanghae99miniproject.comment.dto.CommentSaveRequestDto;
import com.mini.hanghae99miniproject.comment.dto.CommentSaveResponseDto;
import com.mini.hanghae99miniproject.comment.service.CommentService;
import com.mini.hanghae99miniproject.common.response.DataResponse;
import com.mini.hanghae99miniproject.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.mini.hanghae99miniproject.common.response.ResponseMessage.CREATE_COMMENT_SUCCESS_MSG;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/api/posts/{id}/comments")
    public DataResponse<CommentSaveResponseDto> createCommnet(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long postId, @RequestBody CommentSaveRequestDto requestDto) {
        CommentSaveResponseDto commentSaveResponseDto = commentService.createComment(requestDto.toCommentDto(), userDetails.getMember(), postId);
        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG,commentSaveResponseDto);
    }
}