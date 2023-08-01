package com.blog.config;

import com.blog.domain.Member;
import com.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;

@RequiredArgsConstructor
public class BlogMockSecurityContext implements WithSecurityContextFactory<BlogMockUser> {

    private final MemberRepository memberRepository;

    @Override
    public SecurityContext createSecurityContext(BlogMockUser annotation) {
        var member = Member.builder()
                .email(annotation.email())
                .name(annotation.name())
                .password(annotation.password())
                .build();

        memberRepository.save(member);

        var principal = new UserPrincipal(member);

        var role = new SimpleGrantedAuthority("ROLE_ADMIN");
        var authenticationToken = new UsernamePasswordAuthenticationToken(principal,
                member.getPassword(),
                List.of(role));

        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticationToken);

        return context;
    }
}
