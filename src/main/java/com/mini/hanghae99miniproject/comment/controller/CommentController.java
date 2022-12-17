package com.mini.hanghae99miniproject.comment.controller;

import com.mini.hanghae99miniproject.comment.dto.CommentSaveRequestDto;
import com.mini.hanghae99miniproject.comment.dto.CommentSaveResponseDto;
import com.mini.hanghae99miniproject.comment.dto.CommentUpdateRequestDto;
import com.mini.hanghae99miniproject.comment.dto.CommentUpdateResponseDto;
import com.mini.hanghae99miniproject.comment.service.CommentService;
import com.mini.hanghae99miniproject.common.response.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.mini.hanghae99miniproject.common.response.ResponseMessage.CREATE_COMMENT_SUCCESS_MSG;
import static com.mini.hanghae99miniproject.common.response.ResponseMessage.UPDATE_COMMENT_SUCCESS_MSG;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/api/posts/{id}/comments")
    public DataResponse<CommentSaveResponseDto> createCommnet(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long postId, @RequestBody CommentSaveRequestDto requestDto) {
        CommentSaveResponseDto commentSaveResponseDto = commentService.createComment(requestDto.toCommentDto(), userDetails.getMember(), postId);
        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG, commentSaveResponseDto);
    }

    //댓글 수정
    @PutMapping("/api/posts/comments/{id}")
    public DataResponse<CommentUpdateResponseDto> updateComment(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long commentId, @RequestBody CommentUpdateRequestDto requestDto){
        CommentUpdateResponseDto commentUpdateResponseDto = commentService.createComment(requestDto.toCommentDto(), userDetails.getMember(), commentId);
        return new DataResponse<>(UPDATE_COMMENT_SUCCESS_MSG, commentUpdateResponseDto);
    }
}