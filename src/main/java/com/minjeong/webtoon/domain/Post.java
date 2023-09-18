package com.minjeong.webtoon.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Entity
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id")
    private Webtoon webtoon;

    private Integer number;

    private String content;

    private int heartCnt = 0;

    private float starPost = 0.0f;

    private int starCnt = 0;

    private String authorSay;

    @OneToMany(mappedBy = "post")
    private List<Star> stars = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Heart> hearts = new ArrayList<>();

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @Builder
    public Post(Webtoon webtoon, Integer number, String content, String authorSay) {
        this.webtoon = webtoon;
        this.number = number;
        this.content = content;
        this.authorSay = authorSay;
    }
}
