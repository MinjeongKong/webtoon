package com.minjeong.webtoon.web.controller;

import com.minjeong.webtoon.service.WebtoonService;
import com.minjeong.webtoon.web.dto.response.PostRsDto;
import com.minjeong.webtoon.web.dto.response.WebtoonDetailRsDto;
import com.minjeong.webtoon.web.dto.response.WebtoonRsDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webtoon")
public class WebtoonController {

    private final WebtoonService webtoonService;

    @GetMapping("/list")
    @Operation(summary = "개별 웹툰 조회")
    public List<WebtoonDetailRsDto> findWebtoon(@RequestParam Long webtoonId, @RequestParam Long memberId) {
        return webtoonService.findWebtoon(webtoonId, memberId);
    }

    @GetMapping("/list/posts")
    @Operation(summary = "개별 웹툰 페이지 조회")
    public Page<PostRsDto> findPage(@RequestParam Long webtoonId,
                                       @PageableDefault(size = 30, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return webtoonService.findPage(webtoonId, pageable);
    }

    @GetMapping("/recommend")
    public List<WebtoonRsDto> findRecommendWebtoon(@RequestParam(required = false) String tap) {
        if (tap.isEmpty()) {
            return webtoonService.findRandomNewWebtoon();
        }

        DayOfWeek day = DayOfWeek.valueOf(tap);
        return webtoonService.findRecommendDayWebtoon(day);
    }
}
