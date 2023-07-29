package com.blog.controller;

import com.blog.config.data.UserSession;
import com.blog.request.PostCreate;
import com.blog.request.PostEdit;
import com.blog.request.PostSearch;
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

    @GetMapping("/foo")
    public String foo(UserSession userSession) {
        log.info(">>>{}", userSession.name);
        return userSession.name;
    }

    @GetMapping("/bar")
    public String bar() {
        return "인증이 필요 없는 페이지";
    }

    @PostMapping("/posts") // POST -> 200, 201
    public void post(@RequestBody @Valid PostCreate request) {
        request.validate();
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
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}