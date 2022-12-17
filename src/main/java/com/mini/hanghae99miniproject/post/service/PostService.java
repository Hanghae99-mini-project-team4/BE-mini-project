package com.mini.hanghae99miniproject.post.service;

import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.post.dto.RequestPostDto;
import com.mini.hanghae99miniproject.post.dto.ResponsePostDto;
import com.mini.hanghae99miniproject.post.entity.Post;
import com.mini.hanghae99miniproject.post.mapper.PostMapper;
import com.mini.hanghae99miniproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.mini.hanghae99miniproject.common.exception.ExceptionMessage.NO_EXIST_POSTING_ERROR_MSG;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    //게시글 등록
    @Transactional
    public ResponsePostDto createPost(RequestPostDto requestPostDto, Member member) {
        //DB에 게시글 저장
        Post post = postMapper.toEntity(requestPostDto, member);
        postRepository.save(post);

        return postMapper.postToResponsePostDto(post);
    }

    //게시글 전체 조회
    @Transactional(readOnly = true)
    public List<ResponsePostDto> findAllPost() {
        //DB에 저장되어있는 게시글 전부 가져오기
        List<Post> postList = postRepository.findAll();
        List<ResponsePostDto> result = new ArrayList<>();
        for (Post post : postList) {
            result.add(new ResponsePostDto(post));
        }
        return result;
    }

    //게시글 선택 조회
    @Transactional(readOnly = true)
    public ResponsePostDto findOnePost(Long id) {
        //DB에서 게시글 하나 읽어와서 조회 없으면 예외처리
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_POSTING_ERROR_MSG.getMsg())
        );

        return new ResponsePostDto(post);
    }

    //게시글 수정
    public ResponsePostDto updatePost(Long id, RequestPostDto requestPostDto, Member member) {
        //게시글이 있는지 없는지 조회 없으면 예외처리
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_POSTING_ERROR_MSG.getMsg())
        );

        post.update(requestPostDto.getTitle(), requestPostDto.getContent());
        postRepository.save(post);

        return new ResponsePostDto(post);
    }
}
