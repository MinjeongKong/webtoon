package com.minjeong.webtoon.service;

import com.minjeong.webtoon.repository.webtoon.WebtoonRepository;
import com.minjeong.webtoon.web.dto.response.PostRsDto;
import com.minjeong.webtoon.web.dto.response.WebtoonDetailRsDto;
import com.minjeong.webtoon.web.dto.response.WebtoonRsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebtoonService {

    private final WebtoonRepository repository;


    public Page<PostRsDto> findPage(Long webtoonId, Pageable pageable) {
        return repository.findPostPage(webtoonId, pageable);
    }

    public List<WebtoonDetailRsDto> findWebtoon(Long webtoonId, Long memberId) {
        return repository.findWebtoon(webtoonId, memberId);
    }

    public List<WebtoonRsDto> findRandomNewWebtoon() {
        return null;
    }

    public List<WebtoonRsDto> findRecommendDayWebtoon(DayOfWeek day) {
        return null;
    }
}
