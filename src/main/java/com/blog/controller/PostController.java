package com.blog.controller;

import com.blog.request.PostCreate;
import com.blog.response.PostResponse;
import com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController // Controller + ResponseBody
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request){
        // POST -> 200, 201
        postService.write(request);
    }

    /**
     * /posts/{postId} -> 글 한개만 조회 (1개의 Post)
     * /posts -> 글 전체 조회(검색 + 페이징)
     */
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList() {
        return postService.getList();
    }
}