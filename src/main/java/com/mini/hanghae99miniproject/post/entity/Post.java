package com.mini.hanghae99miniproject.post.entity;

import com.mini.hanghae99miniproject.comment.entity.Comment;
import com.mini.hanghae99miniproject.common.entity.Timestamped;
import com.mini.hanghae99miniproject.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    //추천수는 초기에 생성시 무조건 0으로 시작한다.
    //0으로 설정하면 밑에 Builder 오류 발생 안한다.
    @Column(columnDefinition = "integer default 0")
    private Integer recommend;

    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes;

    @Builder
    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
