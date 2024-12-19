package com.ll.rest.domain.post.post.Controller;

import com.ll.rest.domain.post.post.dto.PostDto;
import com.ll.rest.domain.post.post.entity.Post;
import com.ll.rest.domain.post.post.service.PostService;
import com.ll.rest.global.jpa.entity.BaseTime;
import com.ll.rest.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class ApiV1PostController extends BaseTime {
    private final PostService postService;

    @GetMapping
    public List<PostDto> getItems() {
        return postService.findAllByOrderByIdDesc()
                .stream()
                .map(PostDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public PostDto getItems (@PathVariable long id) {
        return  postService.findById(id)
                .map(PostDto::new)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public RsData<Void> deleteItem(@PathVariable long id){
        Post post = postService.findById(id).get();

        postService.delete(post);

        return new RsData<>("200-1", "%d번 글을 삭제했습니다.".formatted(id));
    }

    record PostModifyReqBody(
            @NotBlank @Length(min = 2) String title,
            @NotBlank @Length(min = 2) String content){
    }

    @PutMapping("/{id}")
    @Transactional
    public RsData<PostDto> modifyItem(@PathVariable long id, @RequestBody @Valid PostModifyReqBody reqBody){
        Post post = postService.findById(id).get();

        postService.modify(post, reqBody.title, reqBody.content);

        return new RsData<>("200-1","%d번 글을 수정했습니다.".formatted(id),
                new PostDto(post)
        );
    }

    record PostWriteReqBody(
            @NotBlank @Length(min = 2) String title,
            @NotBlank @Length(min = 2) String content){
    }

    record PostWriteResBody(
            PostDto item,
            long totalCount) {
    }

    @PostMapping
    public RsData<PostWriteResBody> writeItem(@RequestBody @Valid PostWriteReqBody reqBody){
        Post post = postService.write(reqBody.title, reqBody.content);

        return new RsData<>(
                "200-1", "%d번 글을 등록했습니다.".formatted(post.getId()),
                 new PostWriteResBody(
                         new PostDto(post) , postService.count()
                 )
        );

    }

}