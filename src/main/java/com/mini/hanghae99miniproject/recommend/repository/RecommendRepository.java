package com.mini.hanghae99miniproject.recommend.repository;

import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.post.entity.Post;
import com.mini.hanghae99miniproject.recommend.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    //로그인한 유저와 게시글을 인자로 받고 해당 유저가 추천을 한적이 있는지 없는지 확인
    Optional<Recommend> findByUseridAndPost(String userid, Post post);
}
