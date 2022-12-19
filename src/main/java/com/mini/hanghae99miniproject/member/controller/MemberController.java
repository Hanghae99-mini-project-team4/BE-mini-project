package com.mini.hanghae99miniproject.member.controller;

import com.mini.hanghae99miniproject.common.response.Response;
import com.mini.hanghae99miniproject.common.response.ResponseMessage;
import com.mini.hanghae99miniproject.member.dto.LoginRequestDto;
import com.mini.hanghae99miniproject.member.dto.SignupRequestDto;
import com.mini.hanghae99miniproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/signup")
    public Response signup(@RequestBody @Valid SignupRequestDto signupRequestDto){
        memberService.signup(signupRequestDto);
        return new Response(ResponseMessage.SIGNUP_USER_SUCCESS_MSG);
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        memberService.login(loginRequestDto,response);
        return new Response(ResponseMessage.LOGIN_USER_SUCCESS_MSG);
    }
}
