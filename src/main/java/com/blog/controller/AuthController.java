package com.blog.controller;

import com.blog.domain.Member;
import com.blog.exception.InvalidRequest;
import com.blog.exception.InvalidSigninInformation;
import com.blog.repository.MemberRepository;
import com.blog.request.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberRepository memberRepository;

    @PostMapping("/auth/login")
    public Member login(@RequestBody Login login) {
        // json 아이디/비밀번호
        log.info(">>>login={}", login);

        // DB에서 조회
        Member member =  memberRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(() -> new InvalidSigninInformation());

        // 토큰을 응답
        return member;
    }
}
