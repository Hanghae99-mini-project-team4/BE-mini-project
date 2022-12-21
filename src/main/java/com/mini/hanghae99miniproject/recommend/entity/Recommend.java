package com.mini.hanghae99miniproject.recommend.entity;

import com.mini.hanghae99miniproject.member.entity.Member;
import com.mini.hanghae99miniproject.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Recommend(Member member, Post post) {
        this.userid = member.getUserid();
        this.post = post;

    }
}
