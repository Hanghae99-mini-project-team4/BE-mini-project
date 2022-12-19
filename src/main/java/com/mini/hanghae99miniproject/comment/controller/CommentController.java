package com.mini.hanghae99miniproject.comment.controller;

import com.mini.hanghae99miniproject.comment.dto.*;
import com.mini.hanghae99miniproject.comment.service.CommentService;
import com.mini.hanghae99miniproject.common.response.DataResponse;
import com.mini.hanghae99miniproject.common.response.Response;
import com.mini.hanghae99miniproject.post.entity.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.mini.hanghae99miniproject.common.response.ResponseMessage.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/api/posts/{id}/comments")
    public DataResponse<CommentSaveResponseDto> createCommnet(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @RequestBody CommentSaveRequestDto requestDto) {
        CommentSaveResponseDto commentSaveResponseDto = commentService.createComment(requestDto.toCommentDto(), userDetails.getMember(), postId);
        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG, commentSaveResponseDto);
    }

    //댓글 작성 Version2
    @PostMapping("/api/posts/{id}/comments")
    public DataResponse<ResponseComment> createCommnet2(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @RequestBody RequestComment requestComment) {
        ResponseComment responseComment = commentService.createComment2(requestComment, userDetails.getMember(), postId);
        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG, responseComment);
    }

    //댓글 수정
    @PutMapping("/api/posts/comments/{id}")
    public DataResponse<CommentUpdateResponseDto> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId, @RequestBody CommentUpdateRequestDto requestDto){
        CommentUpdateResponseDto commentUpdateResponseDto = commentService.updateComment(requestDto.toCommentDto(), userDetails.getMember(), commentId);
        return new DataResponse<>(UPDATE_COMMENT_SUCCESS_MSG, commentUpdateResponseDto);
    }

    //댓글 수정 Version2
    @PutMapping("/api/posts/comments/{id}")
    public DataResponse<ResponseComment> updateComment2(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId, @RequestBody RequestComment requestComment){
        ResponseComment responseComment = commentService.updateComment2(requestComment, userDetails.getMember(), commentId);
        return new DataResponse<>(UPDATE_COMMENT_SUCCESS_MSG, responseComment);
    }

    //댓글 삭제
    @DeleteMapping("/api/posts/comments/{id}")
    public Response deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {
        commentService.deleteComment(userDetails.getMember(), commentId);
        return new Response((DELETE_COMMENT_SUCCESS_MSG));
    }

    //댓글 삭제 Version2
    @DeleteMapping("/api/posts/comments/{id}")
    public Response deleteComment2(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {
        commentService.deleteComment2(userDetails.getMember(), commentId);
        return new Response((DELETE_COMMENT_SUCCESS_MSG));
    }

}