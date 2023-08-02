package com.blog.controller;

import com.blog.config.UserPrincipal;
import com.blog.request.post.PostCreate;
import com.blog.request.post.PostEdit;
import com.blog.request.post.PostSearch;
import com.blog.response.PostResponse;
import com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@RestController // Controller + ResponseBody
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // TODO MemberSession 받아서
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/posts") // POST -> 200, 201
    public void post(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody @Valid PostCreate request) {
        postService.write(userPrincipal.getUserId(), request);
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request) {
        postService.edit(postId, request);
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasRole('ROLE_ADMIN') && hasPermission(#postId, 'POST', 'DELETE')")
    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}