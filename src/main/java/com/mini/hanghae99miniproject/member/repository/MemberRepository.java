package com.mini.hanghae99miniproject.member.repository;

import com.mini.hanghae99miniproject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByKakaoId(Long kakaoId);

    Optional<Member> findByUserid(String userid);

    Optional<Member> findByNickname(String nickname);
}
