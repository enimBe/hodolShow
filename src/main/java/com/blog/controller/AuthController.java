package com.blog.controller;

import com.blog.config.AppConfig;
import com.blog.request.Login;
import com.blog.request.Signup;
import com.blog.response.SessionResponse;
import com.blog.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.rmi.AlreadyBoundException;
import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AppConfig appConfig;

    // TODO 테스트짜기
    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody Login login) {
        Long memberId = authService.signin(login);

        SecretKey key = Keys.hmacShaKeyFor(appConfig.getJwkKey());

        String jws = Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .signWith(key)
                .setIssuedAt(new Date())
                .compact();

        return new SessionResponse(jws);
    }

    @PostMapping("/auth/signup")
    public void signup(@RequestBody Signup signup) throws AlreadyBoundException {
        authService.signup(signup);

    }
}
