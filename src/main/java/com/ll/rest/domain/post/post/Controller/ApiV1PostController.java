package com.ll.rest.domain.post.post.Controller;

import com.ll.rest.domain.post.post.entity.Post;
import com.ll.rest.domain.post.post.service.PostService;
import com.ll.rest.global.jpa.entity.BaseTime;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class ApiV1PostController extends BaseTime {
    private final PostService postService;

    @GetMapping
    public List<Post> getItems() {
        return postService.findAllByOrderByIdDesc();
    }

    @GetMapping("/{id}")
    public Post getItems (@PathVariable long id) {
        return postService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public Map<String , Object> deleteItem(@PathVariable long id){
        Post post = postService.findById(id).get();

        postService.delete(post);

        Map<String , Object> rsData = new HashMap<>();
        rsData.put("resultCode","200-1");
        rsData.put("msg","%d번 글을 삭제하였습니다.".formatted(id));

        return rsData;
    }


}