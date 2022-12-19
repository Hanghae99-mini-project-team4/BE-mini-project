package com.mini.hanghae99miniproject.post.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String userid;

    //Post랑 양방향 설정 완료.
    @ManyToOne
    @JoinColumn
    private Post post;

}
