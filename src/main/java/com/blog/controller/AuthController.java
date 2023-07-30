package com.blog.controller;

import com.blog.request.Login;
import com.blog.response.SessionResponse;
import com.blog.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private static final String KEY = "T5sEcvjrPoUf6FkewBZH1FBeCLQYKnoBHx3XSpfUmXI=";
    private final AuthService authService;

    // TODO 테스트짜기
    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody Login login) {
        Long memberId = authService.signin(login);

        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(KEY));

        String jws = Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .signWith(key)
                .compact();

        return new SessionResponse(jws);
    }
}
