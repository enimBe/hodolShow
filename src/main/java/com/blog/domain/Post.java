package com.blog.domain;

import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn
    private Member member;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments;

    @Builder
    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void edit(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getMemberId() {
        return this.member.getId();
    }

    public void addComment(Comment comment) {
        comment.setPost(this);
        this.comments.add(comment);
    }
}