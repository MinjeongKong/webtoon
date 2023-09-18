package com.minjeong.webtoon.web.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WebtoonRsDto {

    private Long webtoonId;
    private String title;
    private String author;
    private String thumbnail;
    private float starWebtoon = 0.0f;
    private boolean isNew = false;
    private int postCnt = 0;

    @QueryProjection
    public WebtoonRsDto(Long webtoonId, String title, String author, String thumbnail,
                        float starWebtoon, boolean isNew, int postCnt) {
        this.webtoonId = webtoonId;
        this.title = title;
        this.author = author;
        this.thumbnail = thumbnail;
        this.starWebtoon = starWebtoon;
        this.isNew = isNew;
        this.postCnt = postCnt;
    }
}
