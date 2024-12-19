package com.ll.rest.domain.post.post.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.rest.domain.post.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto{
    private long id;
    @JsonIgnore
    private LocalDateTime createDate;
    @JsonIgnore
    private LocalDateTime modifyDate;
    private String titile;
    private String content;

    public PostDto(Post post) {
        this.id = post.getId();
        this.createDate = post.getCreateDate();
        this.modifyDate = post.getModifiedDate();
        this.titile = post.getTitle();
        this.content = post.getContent();
    }

    public LocalDateTime getCreatedDateTime(){
        return createDate;
    }

    public LocalDateTime getModifiedDateTime(){
        return modifyDate;
    }



}