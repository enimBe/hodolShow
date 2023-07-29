package com.blog.controller;

import com.blog.domain.Member;
import com.blog.exception.InvalidRequest;
import com.blog.exception.InvalidSigninInformation;
import com.blog.repository.MemberRepository;
import com.blog.request.Login;
import com.blog.response.SessionResponse;
import com.blog.service.AuthService;
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

    private final AuthService authService;

    // TODO 테스트짜기
    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody Login login) {
        // json 아이디/비밀번호 -> DB에서 조회 -> 토큰을 응답
        String accessToken = authService.signin(login);
        return new SessionResponse(accessToken);
    }
}
