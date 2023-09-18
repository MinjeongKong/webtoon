package com.minjeong.webtoon.web.dto.response;

import com.minjeong.webtoon.domain.Genre;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@NoArgsConstructor
@Data
public class WebtoonDetailRsDto {

    private Long webtoonId;
    private String title;
    private String author;
    private String content;
    private String thumbnail;
    private String keyword;
    private Genre genre;
    private DayOfWeek dayOfWeek;
    private int lastRead;
    private int favoriteCnt;
    private int postCnt; //전체

    @QueryProjection
    public WebtoonDetailRsDto(Long webtoonId, String title, String author, String content,
                              String thumbnail, String keyword, Genre genre, DayOfWeek dayOfWeek,
                              int lastRead, int favoriteCnt, int postCnt) {
        this.webtoonId = webtoonId;
        this.title = title;
        this.author = author;
        this.content = content;
        this.thumbnail = thumbnail;
        this.keyword = keyword;
        this.genre = genre;
        this.dayOfWeek = dayOfWeek;
        this.lastRead = lastRead;
        this.favoriteCnt = favoriteCnt;
        this.postCnt = postCnt;
    }
}
