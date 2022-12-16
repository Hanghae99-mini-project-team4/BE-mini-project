//package com.mini.hanghae99miniproject.common;
//
//public class 예시 {
//
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/posts/{postId}/comments")
//@RequiredArgsConstructor
//public class CommentController {
//    private final CommentService commentService;
//
//    @PostMapping()
//    public DataResponse<ResponseComment> createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @RequestBody RequestComment requestComment) {
//        String username = userDetails.getUsername();
//        ResponseComment response = commentService.createComment(username,postId, requestComment);
//        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG, response);
//
//    }
//
//    // 댓글 및 대댓글 수정
//    @PutMapping("/{commentId}")
//    public DataResponse<ResponseComment> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId, @RequestBody RequestComment requestComment) {
//        String username = userDetails.getUsername();
//        ResponseComment response = commentService.updateComment(username, commentId, requestComment);
//        return new DataResponse<>(UPDATE_COMMENT_SUCCESS_MSG, response);
//    }
//
//    // 댓글 및 대댓글 삭제
//    @DeleteMapping("/{commentId}")
//    public Response deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long commentId) {
//        String username = userDetails.getUsername();
//        commentService.deleteComment(username, commentId);
//        return new Response(DELETE_COMMENT_SUCCESS_MSG);
//    }
//
//    @PostMapping("/{commentId}")
//    public DataResponse<ResponseComment> createNestedComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @PathVariable Long commentId, @RequestBody RequestComment requestComment) {
//        String username = userDetails.getUsername();
//
//        ResponseComment response = commentService.createNestedComment(username, postId, commentId, requestComment);
//        return new DataResponse<>(CREATE_COMMENT_SUCCESS_MSG, response);
//    }
//
//}
//
//}