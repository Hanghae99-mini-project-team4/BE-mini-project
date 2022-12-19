package com.mini.hanghae99miniproject.member.service;

import com.mini.hanghae99miniproject.common.exception.ExceptionMessage;
import com.mini.hanghae99miniproject.member.dto.LoginRequestDto;
import com.mini.hanghae99miniproject.member.dto.SignupRequestDto;
import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.member.entity.MemberRoleEnum;
import com.mini.hanghae99miniproject.member.repository.MemberRepository;
import com.mini.hanghae99miniproject.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.mini.hanghae99miniproject.common.exception.ExceptionMessage.ADMIN_TOKEN_MISMATCH_ERROR_MSG;
import static com.mini.hanghae99miniproject.common.exception.ExceptionMessage.DUPLICATE_USER_ERROR_MSG;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Value("{admin.token}")
    private String ADMIN_TOKEN;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {

        String userid = signupRequestDto.getUserid();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();

        //회원 중복확인
        Optional<Member> found = memberRepository.findByUserid(userid);
        if(found.isPresent()) {
            throw new IllegalArgumentException(DUPLICATE_USER_ERROR_MSG.getMsg());
        }
        MemberRoleEnum role = MemberRoleEnum.MEMBER;
        if(signupRequestDto.isAdmin()){
            if(!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException(ADMIN_TOKEN_MISMATCH_ERROR_MSG.getMsg());
            }
            role = MemberRoleEnum.ADMIN;
        }

        Member member = new Member(userid, password, nickname, role);
        memberRepository.save(member);
    }

    @Transactional
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response){

        String inputUserid = loginRequestDto.getUserid();
        String inputPassword = loginRequestDto.getPassword();

        Member member = memberRepository.findByUserid(inputUserid).orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.USER_NOT_FOUND_ERROR_MSG.getMsg()));
        if(!passwordEncoder.matches(inputPassword,member.getPassword())){
            throw new IllegalArgumentException(ExceptionMessage.PASSWORDS_DO_NOT_MATCH_ERROR_MSG.getMsg());
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getUserid(),member.getRole()));
    }
}
