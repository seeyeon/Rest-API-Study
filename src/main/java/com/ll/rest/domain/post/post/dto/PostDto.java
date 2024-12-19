package com.ll.rest.domain.post.post.dto;

import com.ll.rest.domain.post.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto{
    private long id;
    private LocalDateTime createdDatetime;
    private LocalDateTime modifiedDatetime;
    private String titile;
    private String content;

    public PostDto(Post post) {
        this.id = post.getId();
        this.createdDatetime = post.getCreateDate();
        this.modifiedDatetime = post.getModifiedDate();
        this.titile = post.getTitle();
        this.content = post.getContent();
    }
}