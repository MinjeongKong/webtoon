package com.minjeong.webtoon.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String imageUrl;

    private String uploadName;

    private boolean isThumbnail = false; //회차 썸네일 여부 (웹툰 대표 사진 X)

    @Builder
    public Image(Post post, String imageUrl, String uploadName) {
        this.post = post;
        this.imageUrl = imageUrl;
        this.uploadName = uploadName;
    }
}
