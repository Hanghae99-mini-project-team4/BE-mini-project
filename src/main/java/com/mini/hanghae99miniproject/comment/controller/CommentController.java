package com.mini.hanghae99miniproject.comment.controller;

import com.mini.hanghae99miniproject.comment.dto.RequestComment;
import com.mini.hanghae99miniproject.comment.dto.ResponseComment;
import com.mini.hanghae99miniproject.comment.service.CommentService;
import com.mini.hanghae99miniproject.common.response.DataResponse;
import com.mini.hanghae99miniproject.common.response.Response;
import com.mini.hanghae99miniproject.common.response.ResponseMessage;
import com.mini.hanghae99miniproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //댓글 작성 Version2
    @PostMapping("/api/posts/{postId}/comments")
    public DataResponse<ResponseComment> createCommnet(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @RequestBody RequestComment requestComment) {
        ResponseComment responseComment = commentService.createComment(requestComment, userDetails.getMember(), postId);
        return new DataResponse<>(ResponseMessage.CREATE_COMMENT_SUCCESS_MSG, responseComment);
    }

    //댓글 수정 Version2
    @PutMapping("/api/posts/comments/{commentId}")
    public DataResponse<ResponseComment> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId, @RequestBody RequestComment requestComment){
        ResponseComment responseComment = commentService.updateComment(requestComment, userDetails.getMember(), commentId);
        return new DataResponse<>(ResponseMessage.UPDATE_COMMENT_SUCCESS_MSG, responseComment);
    }

    //댓글 삭제 Version2
    @DeleteMapping("/api/posts/comments/{commentId}")
    public Response deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {
        commentService.deleteComment(userDetails.getMember(), commentId);
        return new Response((ResponseMessage.DELETE_COMMENT_SUCCESS_MSG));
    }

}