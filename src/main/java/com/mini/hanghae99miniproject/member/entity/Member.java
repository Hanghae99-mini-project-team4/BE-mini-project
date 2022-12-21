package com.mini.hanghae99miniproject.member.entity;


import com.mini.hanghae99miniproject.comment.entity.Comment;
import com.mini.hanghae99miniproject.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userid;

    @Column
    private Long kakaoId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRoleEnum role;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    public Member(String userid, String password, String nickname, MemberRoleEnum role) {
        this.userid = userid;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public Member(String email, Long kakaoId, String password, String nickname, MemberRoleEnum role) {
        this.userid = email;
        this.kakaoId = kakaoId;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public Member kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }
}
