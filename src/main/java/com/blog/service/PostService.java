package com.blog.service;

import com.blog.domain.Post;
import com.blog.exception.PostNotFound;
import com.blog.exception.UserNotFound;
import com.blog.repository.MemberRepository;
import com.blog.repository.PostRepository;
import com.blog.request.PostCreate;
import com.blog.request.PostEdit;
import com.blog.request.PostSearch;
import com.blog.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public void write(Long userId, PostCreate postCreate) {
        var member = memberRepository.findById(userId)
                .orElseThrow(UserNotFound::new);

        // postCreate -> Entity
        Post post = Post.builder()
                .member(member)
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();

        postRepository.save(post);
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public List<PostResponse> getList(PostSearch postSearch) {
        return postRepository.getList(postSearch).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, PostEdit postEdit) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        // PostEditor는 수정할 수 있느 필드를 제한할 때
//        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();
//
//        PostEditor postEditor = editorBuilder.title(postEdit.getTitle())
//                .content(postEdit.getContent())
//                .build();

        post.edit(
                postEdit.getTitle() != null ? postEdit.getTitle() : post.getTitle(),
                postEdit.getContent() != null ? postEdit.getContent() : post.getContent());
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        postRepository.delete(post);
    }
}

/**
 * Controller ->   Service     -> Repository
 * + WebService
 */