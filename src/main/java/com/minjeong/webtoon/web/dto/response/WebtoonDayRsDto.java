package com.minjeong.webtoon.web.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;

@NoArgsConstructor
@Data
public class WebtoonDayRsDto {

    private DayOfWeek dayOfWeek;
    private List<WebtoonRsDto> webtoons;

    @QueryProjection
    public WebtoonDayRsDto(DayOfWeek dayOfWeek, List<WebtoonRsDto> webtoons) {
        this.dayOfWeek = dayOfWeek;
        this.webtoons = webtoons;
    }
}
