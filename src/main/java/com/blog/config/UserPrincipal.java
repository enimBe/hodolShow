package com.blog.config;

import com.blog.domain.Member;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserPrincipal extends User {

    private final Long userId;

    public Long getUserId() {
        return userId;
    }

    public UserPrincipal(Member member) {
        super(member.getEmail(), member.getPassword(), List.of(new SimpleGrantedAuthority("ADMIN")));
        this.userId = member.getId();
    }


}
