package com.mini.hanghae99miniproject.member.dto;

import com.mini.hanghae99miniproject.member.entity.MemberRoleEnum;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String userid;
    private String password;
    private String nickname;
    private boolean isAdmin = false;
    private MemberRoleEnum role;
    private String adminToken = "";
}
