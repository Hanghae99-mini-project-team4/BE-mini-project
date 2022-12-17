package com.mini.hanghae99miniproject.member.controller;

import com.mini.hanghae99miniproject.common.response.Response;
import com.mini.hanghae99miniproject.member.dto.SignupRequestDto;
import com.mini.hanghae99miniproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/signup")
    public Response signup(@RequestBody SignupRequestDto signupRequestDto){
        // json으로 보내는지 form으로 보내는지! -> @RequestBody
        return memberService.signup(signupRequestDto);
    }

    //@PostMapping("/login")
    //public Response login()
}
