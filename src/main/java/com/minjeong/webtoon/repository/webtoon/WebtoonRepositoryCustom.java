package com.minjeong.webtoon.repository.webtoon;

import com.minjeong.webtoon.web.dto.response.PostRsDto;
import com.minjeong.webtoon.web.dto.response.WebtoonDayRsDto;
import com.minjeong.webtoon.web.dto.response.WebtoonDetailRsDto;
import com.minjeong.webtoon.web.dto.response.WebtoonRsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.DayOfWeek;
import java.util.List;

public interface WebtoonRepositoryCustom {

    //개별 웹툰 조회
    List<WebtoonDetailRsDto> findWebtoon(Long webtoonId, Long memberId);

    //개별 웹툰 페이지 조회
    Page<PostRsDto> findPostPage(Long webtoonId, Pageable pageable);

    //주간 전체 조회 - 이달의 신규 웹툰
    List<WebtoonRsDto> findRandomNewWebtoon();

    //주간 전체 조회 - 주간 전체 웹툰
    List<WebtoonDayRsDto> findAllWeekWebtoon();

    //요일별 전체 조회 - 추천 ?요일 웹툰
    List<WebtoonRsDto> findRecommendDayWebtoon(DayOfWeek dayOfWeek);

    //요일별 전체 조회 - 전체 ?요일 웹툰
    List<WebtoonRsDto> findAllDayWebtoon(DayOfWeek dayOfWeek);

    //신작 전체 조회
    List<WebtoonRsDto> findAllNewWebtoon();

    //완결 웹툰 조회
    List<WebtoonRsDto> findAllCompleteWebtoon();

}
