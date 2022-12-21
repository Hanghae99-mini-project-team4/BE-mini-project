package com.mini.hanghae99miniproject.post.entity;

import com.mini.hanghae99miniproject.comment.entity.Comment;
import com.mini.hanghae99miniproject.common.entity.Timestamped;
import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.recommend.entity.Recommend;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 65500)
    private String content;

    @Column(nullable = false)
    private int recommendCount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Recommend> recommendList = new ArrayList<>();

    @Builder
    public Post(String title, String content, Member member, int recommendCount) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.recommendCount = recommendCount;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void like() {
        this.recommendCount += 1;
    }

    public void unlike() {
        this.recommendCount -= 1;
    }
}