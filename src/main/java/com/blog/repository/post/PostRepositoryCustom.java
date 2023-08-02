package com.blog.repository.post;

import com.blog.domain.Post;
import com.blog.request.post.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
