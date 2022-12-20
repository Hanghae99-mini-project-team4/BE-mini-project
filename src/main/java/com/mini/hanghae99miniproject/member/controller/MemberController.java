package com.mini.hanghae99miniproject.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mini.hanghae99miniproject.common.response.Response;
import com.mini.hanghae99miniproject.common.response.ResponseMessage;
import com.mini.hanghae99miniproject.member.dto.CheckRequestDto;
import com.mini.hanghae99miniproject.member.dto.LoginRequestDto;
import com.mini.hanghae99miniproject.member.dto.SignupRequestDto;
import com.mini.hanghae99miniproject.member.service.KakaoService;
import com.mini.hanghae99miniproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final KakaoService kakaoService;

    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        memberService.signup(signupRequestDto);
        return new Response(ResponseMessage.SIGNUP_USER_SUCCESS_MSG);
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        memberService.login(loginRequestDto, response);
        return new Response(ResponseMessage.LOGIN_USER_SUCCESS_MSG);
    }

    @PostMapping("/check")
    public Response check(@RequestBody CheckRequestDto checkRequestDto) {
        if (!checkRequestDto.getUserid().isEmpty()) {
            memberService.useridcheck(checkRequestDto.getUserid());
            return new Response(ResponseMessage.USER_DUBLE_CHECK_SUCCESS_MSG);
        }
        memberService.nicknamecheck(checkRequestDto.getNickname());
        return new Response(ResponseMessage.NICK_DUBLE_CHECK_SUCCESS_MSG);
    }
    @GetMapping("/kakao")
    public Response kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        // code: 카카오 서버로부터 받은 인가 코드
        kakaoService.kakaoLogin(code, response);
        return new Response(ResponseMessage.LOGIN_USER_SUCCESS_MSG);
    }

}
