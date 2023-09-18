package com.minjeong.webtoon;

import com.minjeong.webtoon.domain.Genre;
import com.minjeong.webtoon.domain.Post;
import com.minjeong.webtoon.domain.Webtoon;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitWebtoon {

    private final InitWebtoonService initWebtoonService;

    @PostConstruct
    public void init() {
        //initWebtoonService.init();
    }
    @Component
    static class InitWebtoonService {
        @PersistenceContext
        EntityManager em;

        @Transactional
        public void init() {
            for (int i = 1; i <= 30; i++) {
                Webtoon webtoon = Webtoon.builder()
                        .title("제목 "+i)
                        .content("웹툰 내용 소개")
                        .dayOfWeek(DayOfWeek.of(i%7 ==0 ? 7 : i%7))
                        .author("작가 "+i)
                        .genre(Genre.of(i%5 ==0 ? 5 : i%5))
                        .keyword("#판타지 #세계관 #먼치킨")
                        .build();
                em.persist(webtoon);

                for (int j = 1; j <= 100; j++) {
                    Post post = Post.builder()
                            .webtoon(webtoon)
                            .number(j)
                            .content("imgur" + j)
                            .authorSay("작가의 말")
                            .build();
                    em.persist(post);
                }
            }
        }

    }
}
