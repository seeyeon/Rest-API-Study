package com.ll.rest.global.baseInit;

import com.ll.rest.domain.post.post.entity.Post;
import com.ll.rest.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final PostService postService;

    @Autowired
    @Lazy
    private BaseInitData self;
    @Bean
    public ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            self.work1();
        };
    }

    //사전에 샘플 데이터 3개 넣어놓기
    @Transactional
    public void work1() {

        if(postService.count() >0) return;

        Post post1 = postService.write("축구 하실 분?", "14시까지 22명 모집합니다.");
        Post post2 = postService.write("농구 하실 분?", "15시까지 12명 모집합니다.");
        Post post3 = postService.write("배드민턴 하실 분?", "16시까지 6명 모집합니다.");
    }
}