package com.minjeong.webtoon.repository.webtoon;

import com.minjeong.webtoon.domain.PublishStatus;
import com.minjeong.webtoon.web.dto.response.*;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static com.minjeong.webtoon.domain.QImage.image;
import static com.minjeong.webtoon.domain.QPost.post;
import static com.minjeong.webtoon.domain.QWebtoon.webtoon;
import static com.minjeong.webtoon.domain.QWebtoonData.webtoonData;

public class WebtoonRepositoryImpl implements WebtoonRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public WebtoonRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<WebtoonDetailRsDto> findWebtoon(Long webtoonId, Long memberId) {

        WebtoonDetailRsDto response = queryFactory
                .select(new QWebtoonDetailRsDto(
                        webtoon.id,
                        webtoon.title,
                        webtoon.author,
                        webtoon.content,
                        webtoon.thumbnail,
                        webtoon.keyword,
                        webtoon.genre,
                        webtoon.dayOfWeek,
                        webtoonData.lastRead,
                        webtoon.favoriteCnt,
                        webtoon.postCnt))
                .from(webtoon)
                .leftJoin(webtoonData).on(webtoonData.webtoon.eq(webtoon))
                .where(webtoon.id.eq(webtoonId), webtoonData.member.id.eq(memberId))
                .fetchOne();

        List<WebtoonDetailRsDto> list = new ArrayList<>();
        list.add(response);
        return list;
    }

    @Override
    public Page<PostRsDto> findPostPage(Long webtoonId, Pageable pageable) {
        List<PostRsDto> content = queryFactory
                .select(new QPostRsDto(
                        post.id,
                        image.imageUrl,
                        post.content,
                        post.starPost,
                        post.createdDate
                ))
                .from(post)
                .leftJoin(image).on(image.post.eq(post), image.isThumbnail.isTrue())
                .where(post.webtoon.id.eq(webtoonId))
                .orderBy(postSort(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(post)
                .from(post)
                .where(post.webtoon.id.eq(webtoonId))
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
        //TODO 카운트 쿼리 최적화
    }

    @Override
    public List<WebtoonRsDto> findRandomNewWebtoon() {

        List<WebtoonRsDto> response = queryFactory
                .select(new QWebtoonRsDto(
                        webtoon.id,
                        webtoon.title,
                        webtoon.author,
                        webtoon.thumbnail,
                        webtoon.starWebtoon,
                        webtoon.isNew,
                        webtoon.postCnt
                ))
                .from(webtoon)
                .where(webtoon.status.eq(PublishStatus.NEW))
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(3)
                .fetch();

        return response;
    }

    @Override
    public List<WebtoonDayRsDto> findAllWeekWebtoon() {
        return null;
    }

    @Override
    public List<WebtoonRsDto> findRecommendDayWebtoon(DayOfWeek dayOfWeek) {

        List<WebtoonRsDto> response = queryFactory
                .select(new QWebtoonRsDto(
                        webtoon.id,
                        webtoon.title,
                        webtoon.author,
                        webtoon.thumbnail,
                        webtoon.starWebtoon,
                        webtoon.isNew,
                        webtoon.postCnt
                ))
                .from(webtoon)
                .where(webtoon.dayOfWeek.eq(dayOfWeek))
                .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
                .limit(3)
                .fetch();

        return response;
    }

    @Override
    public List<WebtoonRsDto> findAllDayWebtoon(DayOfWeek dayOfWeek) {

        List<WebtoonRsDto> response = queryFactory
                .select(new QWebtoonRsDto(
                        webtoon.id,
                        webtoon.title,
                        webtoon.author,
                        webtoon.thumbnail,
                        webtoon.starWebtoon,
                        webtoon.isNew,
                        webtoon.postCnt
                ))
                .from(webtoon)
                .where(webtoon.dayOfWeek.eq(dayOfWeek))
                .fetch();

        return response;
    }

    @Override
    public List<WebtoonRsDto> findAllNewWebtoon() {

        List<WebtoonRsDto> response = queryFactory
                .select(new QWebtoonRsDto(
                        webtoon.id,
                        webtoon.title,
                        webtoon.author,
                        webtoon.thumbnail,
                        webtoon.starWebtoon,
                        webtoon.isNew,
                        webtoon.postCnt
                ))
                .from(webtoon)
                .where(webtoon.status.eq(PublishStatus.NEW))
                .fetch();

        return response;
    }

    @Override
    public List<WebtoonRsDto> findAllCompleteWebtoon() {

        List<WebtoonRsDto> response = queryFactory
                .select(new QWebtoonRsDto(
                        webtoon.id,
                        webtoon.title,
                        webtoon.author,
                        webtoon.thumbnail,
                        webtoon.starWebtoon,
                        webtoon.isNew,
                        webtoon.postCnt
                ))
                .from(webtoon)
                .where(webtoon.status.eq(PublishStatus.COMPLETE))
                .fetch();

        return response;
    }

    private OrderSpecifier<?> postSort(Pageable page) {
        if (!page.getSort().isEmpty()) {
            for (Sort.Order order : page.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                return new OrderSpecifier(direction, post.createdDate);
            }
        }
        return new OrderSpecifier(Order.DESC, post.createdDate);
    }
}
