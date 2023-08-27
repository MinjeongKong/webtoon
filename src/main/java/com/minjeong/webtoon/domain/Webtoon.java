package com.minjeong.webtoon.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
public class Webtoon extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "webtoon_id")
    private Long id;

    private String title;

    private String author;

    private String content;

    private String thumbnail;

    private String keyword;

    @Enumerated(value = STRING)
    private Genre genre;

    @Enumerated(value = STRING)
    private DayOfWeek dayOfWeek;

    @Enumerated(value = STRING)
    private PublishStatus status = PublishStatus.NEW;

    private int favoriteCnt = 0;

    private float starWebtoon = 0.0f;

    private Integer updateRank = null;

    private boolean isNew = true;

    private int postCnt = 0;

    @Builder
    public Webtoon(String title, String author, String content, String thumbnail, String keyword, Genre genre, DayOfWeek dayOfWeek) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.thumbnail = thumbnail;
        this.keyword = keyword;
        this.genre = genre;
        this.dayOfWeek = dayOfWeek;
    }
}
