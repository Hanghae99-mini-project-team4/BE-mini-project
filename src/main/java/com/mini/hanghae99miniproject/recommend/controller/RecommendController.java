package com.mini.hanghae99miniproject.recommend.controller;

import com.mini.hanghae99miniproject.common.response.Response;
import com.mini.hanghae99miniproject.common.response.ResponseMessage;
import com.mini.hanghae99miniproject.recommend.service.RecommendService;
import com.mini.hanghae99miniproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class RecommendController {

    private final RecommendService recommendService;

    //게시글 추천 / 추천 취소
    @PostMapping("{postId}/recommend")
    public Response recommendPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return recommendService.addRecommend(postId, userDetails.getMember()) ? new Response(ResponseMessage.RECOMMEND_POSTING_SUCCESS_MSG) : new Response(ResponseMessage.RECOMMEND_CANCEL_POSTING_SUCCESS_MSG);
    }

}
