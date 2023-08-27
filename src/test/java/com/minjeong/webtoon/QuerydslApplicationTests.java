package com.minjeong.webtoon;

import com.minjeong.webtoon.domain.QWebtoon;
import com.minjeong.webtoon.domain.Webtoon;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class QuerydslApplicationTests {

    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {
        Webtoon webtoon = Webtoon.builder().build();
        em.persist(webtoon);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QWebtoon qWebtoon = QWebtoon.webtoon;

        Webtoon result = query.selectFrom(qWebtoon).fetchOne();
        Assertions.assertThat(result).isEqualTo(webtoon);
        Assertions.assertThat(result.getId()).isEqualTo(webtoon.getId());
    }
}
