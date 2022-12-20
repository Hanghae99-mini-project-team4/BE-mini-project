package com.mini.hanghae99miniproject.post.service;

import com.mini.hanghae99miniproject.comment.dto.ResponseComment;
import com.mini.hanghae99miniproject.comment.entity.Comment;
import com.mini.hanghae99miniproject.comment.mapper.CommentMapper;
import com.mini.hanghae99miniproject.comment.repository.CommentRepository;
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
import static com.mini.hanghae99miniproject.common.exception.ExceptionMessage.USER_NOT_MATCH_ERROR_MSG;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    //게시글 등록
    @Transactional
    public ResponsePostDto createPost(RequestPostDto requestPostDto, Member member) {
        //DB에 게시글 저장
        Post post = postMapper.toEntity(requestPostDto, member);
        postRepository.save(post);

        return postMapper.postToResponsePostDtoALL(post);
    }

    //게시글 전체 조회
    @Transactional(readOnly = true)
    public List<ResponsePostDto> findAllPost() {
        //DB에 저장되어있는 게시글 전부 가져오기
        List<Post> postList = postRepository.findAll();
        List<ResponsePostDto> result = new ArrayList<>();
        for (Post post : postList) {
            result.add(postMapper.postToResponsePostDtoALL(post));
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

        //선택한 게시글에서 작성한 댓글만 조회
        List<Comment> comments = commentRepository.findAllByPostOrderByCreatedAtDesc(post);
        List<ResponseComment> commentList = new ArrayList<>();
        for (Comment comment : comments) {
            commentList.add(commentMapper.toResponse(comment));
        }

        return postMapper.postToResponsePostDto(post, commentList);
    }

    //게시글 수정
    @Transactional
    public ResponsePostDto updatePost(Long id, RequestPostDto requestPostDto, Member member) {
        //게시글이 있는지 없는지 조회 없으면 예외처리
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_POSTING_ERROR_MSG.getMsg())
        );

        // 게시글 작성자와 멤버 정보가 일치하는지 확인
        if(!post.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException(USER_NOT_MATCH_ERROR_MSG.getMsg());
        }

        post.update(requestPostDto.getTitle(), requestPostDto.getContent());

        return postMapper.postToResponsePostDtoALL(post);
    }

    //게시글 삭제
    @Transactional
    public void deletePost(Long id, Member member) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_POSTING_ERROR_MSG.getMsg())
        );

        // 게시글 작성자와 멤버 정보가 일치하는지 확인
        if(!post.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException(USER_NOT_MATCH_ERROR_MSG.getMsg());
        }

        // 해당 게시글에 관련된 댓글들 삭제
        commentRepository.deleteCommentByPost(post);

        //All로 했을 때 관련없는 게시글이 지워지는지 안지워지는지 테스트
//        commentRepository.deleteAllByPost(post);

        // 게시글 삭제
        postRepository.delete(post);
    }
    //게시글 수정 전 유저 체크
    @Transactional
    public void checkPost(Member member, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException(NO_EXIST_POSTING_ERROR_MSG.getMsg())
        );

        // 게시글 작성자와 멤버 정보가 일치하는지 확인
        if(!post.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException(USER_NOT_MATCH_ERROR_MSG.getMsg());
        }
    }
}
