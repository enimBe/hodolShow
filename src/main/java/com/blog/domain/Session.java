package com.blog.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accessToken;

    @ManyToOne
    private Member member;

    @Builder
    public Session(Member member) {
        this.accessToken = randomUUID().toString();
        this.member = member;
    }
}
