package com.mini.hanghae99miniproject.recommend.service;

import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.post.entity.Post;
import com.mini.hanghae99miniproject.post.repository.PostRepository;
import com.mini.hanghae99miniproject.recommend.entity.Recommend;
import com.mini.hanghae99miniproject.recommend.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.mini.hanghae99miniproject.common.exception.ExceptionMessage.NO_EXIST_POSTING_ERROR_MSG;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final PostRepository postRepository;
    private final RecommendRepository recommendRepository;

    @Transactional
    public boolean addRecommend(Long postId, Member member) {
        // 게시글이 있는지 없는지 조회 없으면 예외처리
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_POSTING_ERROR_MSG.getMsg())
        );

        Optional<Recommend> recommend = recommendRepository.findByUseridAndPost(member.getUserid(), post);

        if (recommend.isPresent()) { //이미 추천이 있을 때
            Recommend alreadyRecommend = recommend.get();
            recommendRepository.delete(alreadyRecommend);
            post.unlike(); // 추천 수 -1
            return false;
        } else { //추천이 없을 때
            recommendRepository.save(new Recommend(member, post));
            post.like(); // 추천 수 +1
            return true;
        }

    }
}
