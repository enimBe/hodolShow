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

    // role: 역할 -> 관리자, 사용자, 매니저 ROLE_xxx
    // authority: 권한 -> 글쓰기, 글읽기, 사용자 정지시키기 xxx

    public UserPrincipal(Member member) {
        super(member.getEmail(), member.getPassword(),
                List.of(
                        new SimpleGrantedAuthority("ROLE_USER")
                        ));
        this.userId = member.getId();
    }


}
