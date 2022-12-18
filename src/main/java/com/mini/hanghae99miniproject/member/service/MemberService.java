package com.mini.hanghae99miniproject.member.service;

import com.mini.hanghae99miniproject.common.exception.ExceptionMessage;
import com.mini.hanghae99miniproject.common.exception.ExceptionResponse;
import com.mini.hanghae99miniproject.common.response.Response;
import com.mini.hanghae99miniproject.common.response.ResponseMessage;
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

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Value("{admin.token}")
    private String ADMIN_TOKEN;

    @Transactional
    public Response signup(SignupRequestDto signupRequestDto) {

        String userid = signupRequestDto.getUserid();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();

        //회원 중복확인
        Optional<Member> found = memberRepository.findByUserid(userid);
        if(found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다");
        }
        MemberRoleEnum role = MemberRoleEnum.MEMBER;
        if(signupRequestDto.isAdmin()){
            if(!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = MemberRoleEnum.ADMIN;
        }

        Member member = new Member(userid, password, nickname, role);
        memberRepository.save(member);
        return new Response(ResponseMessage.SIGNUP_USER_SUCCESS_MSG);
    }

    @Transactional
    public Response login(LoginRequestDto loginRequestDto, HttpServletResponse response){
        String inputUserid = loginRequestDto.getUserid();
        //String inputPassword = passwordEncoder.encode(loginRequestDto.getPassword()); // 이렇게 하면, 새로운 비밀번호를 생성한다. -> 대신 passwordEncoder.mathces를 사용한다.
        String inputPassword = loginRequestDto.getPassword();
        Member member = memberRepository.findByUserid(inputUserid).orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.USER_NOT_FOUND_ERROR_MSG.getMsg()));
        System.out.println("-------------------------------------------------------");
        System.out.println("member.getPassword() = " + member.getPassword());
        System.out.println("inputPassword = " + inputPassword);
        System.out.println("-------------------------------------------------------");
        if(!passwordEncoder.matches(inputPassword,member.getPassword())){
            throw new IllegalArgumentException(ExceptionMessage.PASSWORDS_DO_NOT_MATCH_ERROR_MSG.getMsg());
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getUserid(),member.getRole()));
        return new Response(ResponseMessage.LOGIN_USER_SUCCESS_MSG);
    }
}
